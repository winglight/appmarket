package com.yi4all.appmarketapp.service;

import java.util.List;

import com.yi4all.appmarketapp.AppsTab;
import com.yi4all.appmarketapp.db.AppModel;
import com.yi4all.appmarketapp.db.CategoryModel;
import com.yi4all.appmarketapp.db.CategoryType;
import com.yi4all.appmarketapp.db.UserModel;

public interface IDBService {

	public void close();
	
	//users
	public UserModel queryDefaultUser();
	public UserModel queryUserBySid(String sid);
	public boolean createUser(UserModel user);
	public boolean updateUser(UserModel user);

	//category
	public List<CategoryModel> getAllCategory();
	
	//apps
	public List<AppModel> getAppsByTab(AppsTab currentTab, UserModel currentUser, CategoryType catgegory, int page);
	
	public List<AppModel> getHotApps(int page);
	
	public List<AppModel> getAppsByCategory(CategoryType catgegory, int page);
	
	public List<AppModel> getNewestApps(int page);
	
	public List<AppModel> getUploadedApps(UserModel currentUser, int page);
	
	public void updateApps(List<AppModel> list);

}
