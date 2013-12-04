package com.yi4all.appmarketapp.db;

import java.sql.SQLException;
import java.util.Date;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDBOpenHelper extends OrmLiteSqliteOpenHelper {

	public static final int DATABASE_VERSION = 2;

	public static final String DATABASE_NAME = "issues.db";

	// we do this so there is only one helper
	private static AppDBOpenHelper helper = null;

	private Dao<UserModel, Long> userDao;
	private Dao<AppModel, Long> appDao;
	private Dao<CategoryModel, Long> categoryDao;

	public AppDBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	public static synchronized AppDBOpenHelper getHelper(Context context) {
		if (helper == null) {
			helper = new AppDBOpenHelper(context);
			helper.getWritableDatabase();
		}
		return helper;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(AppDBOpenHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, UserModel.class);
			TableUtils.createTable(connectionSource, CategoryModel.class);
			TableUtils.createTable(connectionSource, AppModel.class);
		} catch (SQLException e) {
			Log.e(AppDBOpenHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int oldVersion, int newVersion) {
		try {
			Log.i(AppDBOpenHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, AppModel.class, true);
			TableUtils.dropTable(connectionSource, UserModel.class, true);
			TableUtils.dropTable(connectionSource, CategoryModel.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(AppDBOpenHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}

	}

	public Dao<UserModel, Long> getUserDAO() throws SQLException {
		if (userDao == null) {
			userDao = getDao(UserModel.class);
		}
		return userDao;
	}

	public Dao<AppModel, Long> getAppDAO() throws SQLException {
		if (appDao == null) {
			appDao = getDao(AppModel.class);
		}
		return appDao;
	}
	
	public Dao<CategoryModel, Long> getCategoryDAO() throws SQLException {
		if (categoryDao == null) {
			categoryDao = getDao(CategoryModel.class);
		}
		return categoryDao;
	}

	@Override
	public void close() {
		super.close();
		appDao = null;
		userDao = null;
		categoryDao = null;
	}

}
