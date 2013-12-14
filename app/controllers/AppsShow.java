package controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;

import models.AppModel;
import models.CategoryModel;
import models.dto.MessageModel;
import models.dto.PageInfo;
import models.dto.SimpleAppModel;
import play.Play;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.Constants;

public class AppsShow extends Controller {
  
    public static Result getHotapps(Long page, Long lastUpdateDate) {
    	List<SimpleAppModel> list = AppModel.findAppsByHots(page.intValue(), (lastUpdateDate > 0?new Date(lastUpdateDate):null));
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
    
    public static Result getNewestapps(Long page, Long lastUpdateDate) {
    	List<SimpleAppModel> list = AppModel.findAppsByNewest(page.intValue(), (lastUpdateDate > 0?new Date(lastUpdateDate):null));
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
    
    public static Result getCategoryapps(Long category, Long page, Long lastUpdateDate) {
    	List<SimpleAppModel> list = AppModel.findAppsByCategory(category, page.intValue(), (lastUpdateDate > 0?new Date(lastUpdateDate):null));
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
    
    public static Result getCategoryTypeapps(Long categorytype, Long page, Long lastUpdateDate) {
    	List<SimpleAppModel> list = AppModel.findAppsByCategoryType(categorytype, page.intValue(), (lastUpdateDate == null?null:new Date(lastUpdateDate)));
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
    
    public static Result checkupdate(Long app) {
    	AppModel am = AppModel.find.byId(app);
    	if(am != null){
		return ok(am.appVersionCode);
    	}else{
    		return ok("-1");
    	}
	}
    
    public static Result downloadApk(String fileName) {

		String path = Play.application().path().getPath() + "/upload/"
				+ ((Secured.isFromMobile())?fileName:AppModel.findMarketAppName());
		try {
			response()
					.setContentType("application/vnd.android.package-archive");
			ByteArrayInputStream baos = new ByteArrayInputStream(
					IOUtils.toByteArray(new FileInputStream(new File(path))));
			
			
			return ok(baos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return notFound(fileName + " is Not Found!");
	}

	public static Result showImage(String filename) {

		String path = Play.application().path().getPath() + "/upload/"
				+ filename;

		try {
			response().setContentType("image");
			ByteArrayInputStream baos = new ByteArrayInputStream(
					IOUtils.toByteArray(new FileInputStream(new File(path))));
			return ok(baos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return notFound(filename + " is Not Found!");
	}
}

