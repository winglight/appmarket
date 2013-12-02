package com.yi4all.appmarketapp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.yi4all.appmarketapp.db.AppDBOpenHelper;
import com.yi4all.appmarketapp.db.AppModel;
import com.yi4all.appmarketapp.db.CategoryModel;
import com.yi4all.appmarketapp.db.UserModel;
import com.yi4all.appmarketapp.util.Constants;

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

			return udao.queryForAll();

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return new ArrayList<CategoryModel>();
	}

	@Override
	public List<AppModel> getAppsByCategory(CategoryModel catgegory, int page) {
		try {
			Dao<AppModel, Long> dba = appsHelper.getAppDAO();
			QueryBuilder<AppModel, Long> queryBuilder = dba.queryBuilder();
			queryBuilder.limit((long) Constants.AMOUNT_PER_PAGE);
			queryBuilder.offset((long) (page - 1) * Constants.AMOUNT_PER_PAGE);
			queryBuilder.orderBy(AppModel.CREATEDAT, false);
			
			Where<AppModel, Long> where = queryBuilder.where();
			if(catgegory != null){
				where.eq(AppModel.CATEGORY, catgegory);
			}
			where.eq(AppModel.DELETEFLAG, false);

			return dba.query(queryBuilder.prepare());

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return new ArrayList<AppModel>();
	}

	@Override
	public void updateApps(List<AppModel> list) {
		try {
			Dao<AppModel, Long> udao = appsHelper.getAppDAO();

			for (AppModel im : list) {				
				AppModel app = udao.queryForId(im.getId());
				if (app == null) {
					//create a new app
					udao.create(im);
				}
			}

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}

	}

	@Override
	public List<AppModel> getHotApps(int page) {
		try {
			Dao<AppModel, Long> dba = appsHelper.getAppDAO();

			QueryBuilder<AppModel, Long> queryBuilder = dba.queryBuilder();
			queryBuilder.limit((long) Constants.AMOUNT_PER_PAGE);
			queryBuilder.offset((long) (page - 1) * Constants.AMOUNT_PER_PAGE);
			queryBuilder.orderBy(AppModel.DOWNLOADS, false);
			queryBuilder.orderBy(AppModel.CREATEDAT, false);
			
			Where<AppModel, Long> where = queryBuilder.where();
			where.eq(AppModel.DELETEFLAG, false);

			return dba.query(queryBuilder.prepare());

		} catch (SQLException e) {

			Log.e(LOG_TAG, e.getMessage());
		}
		return null;
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

	@Override
	public List<AppModel> getNewestApps(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppModel> getAdultApps(int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
