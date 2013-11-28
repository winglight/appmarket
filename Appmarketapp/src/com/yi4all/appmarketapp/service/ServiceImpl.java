package com.yi4all.appmarketapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yi4all.appmarketapp.db.CategoryModel;
import com.yi4all.appmarketapp.db.ImageModel;
import com.yi4all.appmarketapp.db.IssueModel;
import com.yi4all.appmarketapp.db.UserModel;
import com.yi4all.dailyshow.json.MessageModel;
import com.yi4all.dailyshow.json.RemoteInvokeModel;
import com.yi4all.dailyshow.service.enumtype.RemoteInvokeStatus;
import com.yi4all.dailyshow.service.enumtype.RemoteRequestAction;
import com.yi4all.dailyshow.util.DateUtils;
import com.yi4all.dailyshow.util.TimeToken;
import com.yi4all.dailyshow.util.Utils;
import com.yi4all.dailyshow.R;

public class ServiceImpl {

	private static final String LOG_TAG = "ServiceImpl";

	private IDBService dbService;
	private IRemoteService remoteService;
	private UserModel currentUser;
	private Activity context;

	private ObjectMapper mapper;

	private static ServiceImpl instance;

	public static ServiceImpl getInstance(Activity context) {
		if (instance == null) {
			// initDBFile(context);

			instance = new ServiceImpl(context);
		}
		if (instance.remoteService == null) {
			instance.remoteService = RemoteServiceImpl.getInstance();
		}
		return instance;
	}

	private ServiceImpl(Activity context) {
		this.context = context;
		this.dbService = DBServiceImpl.getInstance(context);
		this.remoteService = RemoteServiceImpl.getInstance();

		this.mapper = new ObjectMapper();

		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String sid = manager.getDeviceId();

		this.remoteService.setSid(sid);
	}

	public IDBService getDbService() {
		if (dbService == null) {
			dbService = DBServiceImpl.getInstance(context);
		}
		return dbService;
	}

	private void setCurrentUser(UserModel currentUser) {
		this.currentUser = currentUser;
	}

	interface PushCallBack {
		public void execute(RemoteInvokeModel rim);

	}

	public interface QueryCallBack<T> {
		public void execute(T res);

	}

	/**
	public void addUserConsumedKbytes(int size) {
		if (!sureLogin())
			return;

		// 1.subtract limit kb
		currentUser.setLimitKBytes(currentUser.getLimitKBytes() - size / 1024);

		// 2.submit to the server
		try {
			MessageModel result = remoteService.pushData2Server(createRIM(
					RemoteRequestAction.POST, "/service/user/limitkbytes/sub",
					""));
			if (result.isFlag()) {
				try {
					String response = mapper.writeValueAsString(result
							.getData());
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}

			} else {

			}
		} catch (ServiceException e) {
		}
	}*/

	public boolean sureLogin() {
		if (currentUser == null || !remoteService.isLogin()) {
			MessageModel<UserModel> msg = loginDirect(remoteService.getSid());

			if (msg.isFlag()) {
				currentUser = msg.getData();
				
				TimeToken tt = getMikandiTimeToken();
				if(tt != null){
					long exp = tt.getExpiration();
					currentUser.setExpirationDate(new Date(exp));
				}
			}else{
				return false;
			}
		}
		return true;
	}
	
	public boolean isValid() {
		sureLogin();
		
		return new Date().before(currentUser.getExpirationDate());
	}
	
	//TODO: connect mikandi
	public TimeToken getMikandiTimeToken(){
		return null;
	}

	/********** 同步方法-远程 ****************/
	public UserModel getCurrentUser() {
		if (currentUser == null) {
			currentUser = getDbService().queryDefaultUser();
		}
		return currentUser;
	}

	public boolean createUser(UserModel user) {
		return getDbService().createUser(user);
	}

	public List<IssueModel> getIssueByCategoryRemote(
			List<CategoryModel> currentCategories, int page, Date lastUpdateDate) {
		if (!sureLogin())
			return new ArrayList<IssueModel>();

		try {
			// 2.1.get return value
			TypeReference type = new TypeReference<MessageModel<List<IssueModel>>>() {
			};
			String categoryIds = "";
			if (currentCategories != null) {
				for (CategoryModel cm : currentCategories) {
					categoryIds += cm.getId() + ",";
				}
				if (categoryIds.endsWith(",")) {
					categoryIds = categoryIds.substring(0,
							categoryIds.length() - 1);
				}
			}else{
				categoryIds = "null";
			}
			MessageModel<List<IssueModel>> result = remoteService
					.generalQuery(
							createRIM(
									RemoteRequestAction.GET,
									"/categories/"
											+ categoryIds
											+ "/issues/"
											+ page
											+ "?tokenid="
											+ RemoteServiceImpl.TOKEN_TAG
											+ ((lastUpdateDate != null) ? ("&lastUpdateDate=" + lastUpdateDate
													.getTime()) : ""), null),
							type);
			if (result != null && result.getData() != null) {

				getDbService().updateApps(result.getData());

				return result.getData();
			}

		} catch (ServiceException se) {
			Utils.toastMsg(context, R.string.no_network_connection_toast);
		}

		return new ArrayList<IssueModel>();
	}

	public List<ImageModel> getImageByIssueRemote(IssueModel issue) {
		if (!sureLogin())
			return new ArrayList<ImageModel>();

		List<ImageModel> list = getDbService().getImageByIssue(issue);
		if (list != null && list.size() > 0) {
			return list;
		} else {
			try {
				// 2.1.get return value
				TypeReference type = new TypeReference<MessageModel<List<ImageModel>>>() {
				};
				MessageModel<List<ImageModel>> result = remoteService
						.generalQuery(
								createRIM(RemoteRequestAction.GET, "/issues/"
										+ issue.getId() + "/images", null),
								type);
				if (result != null && result.getData() != null) {

					getDbService().updateImages(result.getData(), issue);

					return result.getData();
				}

			} catch (ServiceException se) {
				Utils.toastMsg(context, R.string.no_network_connection_toast);
			}

			return new ArrayList<ImageModel>();
		}
	}

	public void getAllCategory(final QueryCallBack<List<CategoryModel>> query,
			boolean isRemote) {
		if (!sureLogin())
			return;

		List<CategoryModel> list = getDbService().getAllCategory();

		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					// 2.1.get return value
					TypeReference type = new TypeReference<MessageModel<List<CategoryModel>>>() {
					};

					MessageModel<List<CategoryModel>> result = remoteService
							.generalQuery(
									createRIM(RemoteRequestAction.GET,
											"/categories", null), type);
					if (result != null && result.getData() != null) {
						query.execute(result.getData());

						// Utils.savePref(context, "lastUpdateCategoryTime",
						// String.valueOf(new Date().getTime()));

						getDbService().updateCategories(result.getData());
					}

				} catch (ServiceException se) {

				}

			}
		};

		if (list.size() > 0) {
			query.execute(list);

			if (isRemote) {
				new Thread(run).start();
			}

		} else {
			new Thread(run).start();
		}
	}

	public MessageModel<UserModel> loginDirect(String sid) {
		MessageModel<UserModel> msg = new MessageModel<UserModel>();
		try {
			UserModel user = remoteService.loginDirect();

			// query user from local db
			UserModel user2 = getDbService().queryUserBySid(sid);
			if (user2 == null) {
				// save user into db
				if (!getDbService().createUser(user)) {
					msg.setFlag(false);
					msg.setErrorCode(ServiceException.ERROR_CODE_DB_EXCEPTION);
					return msg;
				}
			} else {
				getDbService().updateUser(user2);
				user = user2;
			}

			setCurrentUser(user);
			msg.setData(user);
			msg.setFlag(true);
		} catch (ServiceException e) {
			Log.e(LOG_TAG, "loginDirect error:" + e.getMessage());
			msg.setErrorCode(e.getErrorCode());
			msg.setMessage(e.getMessage());
		}
		return msg;
	}

	public void pushData2Server(final RemoteInvokeModel rim,
			final PushCallBack pcb) {
		Runnable run = new Runnable() {

			@Override
			public void run() {
				try {
					MessageModel result = remoteService.pushData2Server(rim);
					if (result.isFlag()) {
						rim.setStatus(RemoteInvokeStatus.SUCCEED);
						try {
							String response = mapper.writeValueAsString(result
									.getData());
							rim.setResponseBody(response);
						} catch (JsonProcessingException e) {
							e.printStackTrace();
						}

					} else {
						rim.setStatus(RemoteInvokeStatus.FAILED);
					}
				} catch (ServiceException e) {
					rim.setStatus(RemoteInvokeStatus.FAILED);
				}

				// call back
				if (pcb != null) {
					pcb.execute(rim);
				}
			}
		};
		new Thread(run).start();
	}

	private RemoteInvokeModel createRIM(RemoteRequestAction action, String url,
			String request) {
		RemoteInvokeModel rim = new RemoteInvokeModel();
		// add params for RPC
		rim.setAction(action);
		rim.setRequestBody(request);
		rim.setStatus(RemoteInvokeStatus.ONGOING);
		rim.setUrl(url);

		return rim;
	}

	public void close() {
		if (dbService != null) {
			dbService.close();
			dbService = null;
		}
	}

	public void setURL(String url) {
		remoteService.setBaseUrl(url);
	}

	public String getURL() {
		return remoteService.getBaseUrl();
	}

	public void setContext(Activity context) {
		this.context = context;
	}

}
