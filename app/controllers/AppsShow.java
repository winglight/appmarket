package controllers;

import java.util.List;

import models.AppModel;
import models.CategoryModel;
import models.dto.MessageModel;
import models.dto.PageInfo;
import models.dto.SimpleAppModel;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.Constants;

public class AppsShow extends Controller {
  
    public static Result getHotapps(Long page) {
    	List<SimpleAppModel> list = AppModel.findAppsByHots(page.intValue());
    	PageInfo pageInfo = AppModel.getHotAppsPageInfo(page.intValue());
    	if(list.size() < Constants.AMOUNT_PER_PAGE){
    		pageInfo.setEnd(list.size());
    	}
    	
		MessageModel<List<SimpleAppModel>> mm = new MessageModel<List<SimpleAppModel>>();
		mm.setFlag(true);
		mm.setPage(pageInfo);
		mm.setData(list);
		return ok(Json.toJson(mm));
	}
    
    public static Result getNewestapps(Long page) {
    	List<SimpleAppModel> list = AppModel.findAppsByHots(page.intValue());
    	PageInfo pageInfo = AppModel.getHotAppsPageInfo(page.intValue());
    	if(list.size() < Constants.AMOUNT_PER_PAGE){
    		pageInfo.setEnd(list.size());
    	}
    	
		MessageModel<List<SimpleAppModel>> mm = new MessageModel<List<SimpleAppModel>>();
		mm.setFlag(true);
		mm.setPage(pageInfo);
		mm.setData(list);
		return ok(Json.toJson(mm));
	}
    
    public static Result getCategoryapps(Long category, Long page) {
    	List<SimpleAppModel> list = AppModel.findAppsByHots(page.intValue());
    	PageInfo pageInfo = AppModel.getHotAppsPageInfo(page.intValue());
    	if(list.size() < Constants.AMOUNT_PER_PAGE){
    		pageInfo.setEnd(list.size());
    	}
    	
		MessageModel<List<SimpleAppModel>> mm = new MessageModel<List<SimpleAppModel>>();
		mm.setFlag(true);
		mm.setPage(pageInfo);
		mm.setData(list);
		return ok(Json.toJson(mm));
	}
    
    public static Result getCategories() {
    	List<CategoryModel> list = CategoryModel.all();
    	
		MessageModel<List<CategoryModel>> mm = new MessageModel<List<CategoryModel>>();
		mm.setFlag(true);
		mm.setData(list);
		return ok(Json.toJson(mm));
	}
    
}

