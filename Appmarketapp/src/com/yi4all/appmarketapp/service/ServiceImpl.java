package com.yi4all.appmarketapp.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yi4all.appmarketapp.db.UserModel;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ServiceImpl {

	private static final String LOG_TAG = "ServiceImpl";

	private IDBService dbService;
	private IRemoteService remoteService;
	private UserModel currentUser;
	private Activity context;

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

	public boolean sureLogin() {
		if (currentUser == null || !remoteService.isLogin()) {
			MessageModel<UserModel> msg = loginDirect(remoteService.getSid());

			if (msg.isFlag()) {
				currentUser = msg.getData();
				
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
