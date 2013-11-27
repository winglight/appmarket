package com.yi4all.appmarketapp.service;

import java.util.List;

import com.yi4all.appmarketapp.db.CategoryModel;
import com.yi4all.appmarketapp.db.ImageModel;
import com.yi4all.appmarketapp.db.IssueModel;
import com.yi4all.appmarketapp.db.UserModel;

public interface IDBService {

	public void close();
	
	//users
	public UserModel queryUserByEmail(String email, String password);
	public UserModel queryUserBySid(String sid);
	public UserModel queryDefaultUser();
	public boolean createUser(UserModel user);
	public boolean updateUser(UserModel user);
	
	public List<CategoryModel> getSubscribedCategory();
	
	public List<CategoryModel> getAllCategory();
	
	public List<IssueModel> getIssueByCategory(List<CategoryModel> currentCategories, int page);
	
	public List<ImageModel> getImageByIssue(IssueModel issue);
	
	public void updateCategories(List<CategoryModel> list);
	
	public void updateCategory(CategoryModel cm);
	
	public void updateIssues(List<IssueModel> list);
	
	public void updateImages(List<ImageModel> list, IssueModel im);
}
