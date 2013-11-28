package com.yi4all.appmarketapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yi4all.appmarketapp.db.AppDBOpenHelper;

import android.content.Context;
import android.util.Log;

public class DBServiceImpl implements IDBService {

	private static final String LOG_TAG = "DBServiceImpl";

	private AppDBOpenHelper appsHelper;
	
	private Map<Long, CategoryModel> categoryMap;

	private DBServiceImpl(Context context) {
		// this.commonHelper = CommonDBOpenHelper.getHelper(context);
		this.appsHelper = AppDBOpenHelper.getHelper(context);
	}

	public static IDBService getInstance(Context context) {
		return new DBServiceImpl(context);
	}

	@Override
	public void close() {
		if (appsHelper != null && appsHelper.isOpen()) {
			OpenHelperManager.releaseHelper();
			appsHelper = null;
		}
	}

	public AppDBOpenHelper getUserHelper() {
		return appsHelper;
	}

	@Override
	public UserModel queryUserByEmail(String email, String password) {
		try {
			Dao<UserModel, Long> udao = appsHelper.getUserDAO();

			QueryBuilder<UserModel, Long> queryBuilder = udao.queryBuilder();
			Where<UserModel, Long> where = queryBuilder.where();
			where.eq(UserModel.FIELD_EMAIL, email);
			if (password != null) {
				where.and();
				where.eq(UserModel.FIELD_PASSWORD, password);
			}

			return udao.queryForFirst(queryBuilder.prepare());

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return null;
	}

	@Override
	public boolean createUser(UserModel user) {
		try {
			Dao<UserModel, Long> udao = appsHelper.getUserDAO();

			// 1.query user by phone and email
			if (checkDuplicatedUser(user)) {

			} else {
				udao.create(user);
			}

			return true;
		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return false;
	}

	public boolean checkDuplicatedUser(UserModel user) {
		if (user == null)
			return false;
		try {
			Dao<UserModel, Long> udao = appsHelper.getUserDAO();

			if (user.getEmail() != null && user.getEmail().length() > 0) {
				List<UserModel> list;

				list = udao.queryForEq(UserModel.FIELD_EMAIL, user.getEmail());

				if (list != null && list.size() > 0) {
					return true;
				}
			}
		} catch (SQLException e) {
			Log.e(LOG_TAG, "DB error:" + e.getMessage());
		}
		return false;
	}

	@Override
	public UserModel queryUserBySid(String sid) {
		try {
			Dao<UserModel, Long> udao = appsHelper.getUserDAO();

			List<UserModel> list = udao.queryForEq(UserModel.FIELD_SID, sid);

			if (list != null && list.size() > 0) {
				return list.get(0);
			}

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return null;
	}

	@Override
	public UserModel queryDefaultUser() {
		try {
			Dao<UserModel, Long> udao = appsHelper.getUserDAO();

			List<UserModel> list = udao.queryForAll();

			if (list != null && list.size() > 0) {
				return list.get(0);
			}

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateUser(UserModel user) {
		try {
			Dao<UserModel, Long> udao = appsHelper.getUserDAO();

			udao.update(user);

			return true;
		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return false;
	}

	@Override
	public List<CategoryModel> getAllCategory() {
		try {
			Dao<CategoryModel, Long> udao = appsHelper.getCategoryDAO();

			QueryBuilder<CategoryModel, Long> queryBuilder = udao.queryBuilder();
			queryBuilder.orderBy(IssueModel.CREATED_AT, false);
			
			return udao.query(queryBuilder.prepare());

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return new ArrayList<CategoryModel>();
	}

	@Override
	public List<IssueModel> getIssueByCategory(List<CategoryModel> catgegory, int page) {
		try {
			Dao<IssueModel, Long> dba = appsHelper.getIssueDAO();
			QueryBuilder<IssueModel, Long> queryBuilder = dba.queryBuilder();
			queryBuilder.limit((long) Constants.AMOUNT_PER_PAGE);
			queryBuilder.offset((long) (page - 1) * Constants.AMOUNT_PER_PAGE);
			queryBuilder.orderBy(IssueModel.CREATED_AT, false);

			if(catgegory != null && catgegory.size() > 0){
				Where<IssueModel, Long> where = queryBuilder.where();
				where.in(IssueModel.CATEGORY, catgegory);
			}

			return dba.query(queryBuilder.prepare());

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return new ArrayList<IssueModel>();
	}

	@Override
	public List<ImageModel> getImageByIssue(IssueModel issue) {
		try {
			Dao<ImageModel, Long> dba = appsHelper.getImageDAO();

			QueryBuilder<ImageModel, Long> queryBuilder = dba.queryBuilder();
			queryBuilder.orderBy(ImageModel.ORDER, true);

			Where<ImageModel, Long> where = queryBuilder.where();
			where.eq(ImageModel.ISSUE, issue);

			return dba.query(queryBuilder.prepare());

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return new ArrayList<ImageModel>();
	}

	@Override
	public void updateCategories(List<CategoryModel> list) {
		try {
			Dao<CategoryModel, Long> udao = appsHelper.getCategoryDAO();

			for (CategoryModel cm : list) {
				CategoryModel cm2 = udao.queryForId(cm.getId());
				if (cm2 == null) {
					cm.setSubscribed(true);
					udao.create(cm);
				}else{
					cm2.setCover(cm.getCover());
					cm2.setName(cm.getName());
					udao.update(cm2);
				}
			}

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
	}

	@Override
	public void updateApps(List<IssueModel> list) {
		try {
			Dao<IssueModel, Long> udao = appsHelper.getIssueDAO();

			for (IssueModel im : list) {				
				List<IssueModel> list2 = udao.queryForEq(IssueModel.FIELD_SERVERID, im.getId());
				if (list2 == null || list2.size() == 0) {
					//create a new issue
					im.setCategory(getCategoryMap().get(im.getCategoryId()));
					im.setServerId(im.getId());
					udao.create(im);
					
					updateImages(im.getImages(), im);
				}else{
					//update local issue
					IssueModel im2 = list2.get(0);
					im2.setCover(im.getCover());
					im2.setName(im.getName());
					im2.setImageAmount(im.getImageAmount());
					im2.setCreatedAt(im.getCreatedAt());
					udao.update(im2);
					
//					updateImages(im.getImages(), im2);
				}
			}

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}

	}

	@Override
	public void updateImages(List<ImageModel> list, IssueModel iim) {
		try {
			Dao<ImageModel, Long> udao = appsHelper.getImageDAO();

			for (ImageModel im : list) {
				im.setIssue(iim);
				List<ImageModel> list2 = udao.queryForEq(ImageModel.FIELD_SERVERID, im.getId());
				if (list2 == null || list2.size() == 0) {
					im.setServerId(im.getId());
					udao.create(im);
				}
			}

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}

	}

	@Override
	public List<CategoryModel> getHotApps() {
		try {
			Dao<CategoryModel, Long> udao = appsHelper.getCategoryDAO();

			List<CategoryModel> list = udao.queryForEq(CategoryModel.SUBSCRIBED, true);
			
				return list;

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return null;
	}

	@Override
	public void updateCategory(CategoryModel cm) {
		try {
			Dao<CategoryModel, Long> udao = appsHelper.getCategoryDAO();

			udao.update(cm);

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		
	}

	public Map<Long, CategoryModel> getCategoryMap() {
		if(categoryMap == null){
			categoryMap = new HashMap<Long, CategoryModel>();
			List<CategoryModel> list = getAllCategory();
			for(CategoryModel cm : list){
				categoryMap.put(cm.getId(), cm);
			}
		}
		return categoryMap;
	}

}
