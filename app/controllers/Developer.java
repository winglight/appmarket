package controllers;

import play.Play;
import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.Context;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import static play.data.Form.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import apkReader.ApkInfo;
import apkReader.ApkReader;

import models.*;
import models.dto.MessageModel;

import util.Constants;
import util.Utils;
import views.html.*;

/**
 * Manage projects related operations.
 */
@Security.Authenticated(Secured.class)
public class Developer extends Controller {
  
    /**
     * Display the dashboard.
     */
    public static Result index() {
    	String name = Context.current().session().get(Constants.SESSION_USER_NAME);
    	UserModel user = UserModel.findByloginName(name);
        return ok(
            developer.render(user)
        );
    }
    
    public static Result getApps() {
		MessageModel<List<AppModel>> mm = new MessageModel<List<AppModel>>();
		mm.setFlag(true);
		mm.setData(AppModel.findAppsByAuthor(Secured.getCurrentUser()));
		return ok(Json.toJson(mm));
	}
    
public static Result downloadApk(String fileName) {
		
		String path = Play.application().path().getPath() + "/upload/" + fileName;
		
		try {
			response().setContentType("application/vnd.android.package-archive");
			ByteArrayInputStream baos = new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(new File(path))));
			return ok(baos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return notFound(fileName + " is Not Found!");
	}

public static Result showImage(String filename) {
	
	String path = Play.application().path().getPath() + "/upload/" + filename;
	
	try {
		response().setContentType("image");
		ByteArrayInputStream baos = new ByteArrayInputStream(IOUtils.toByteArray(new FileInputStream(new File(path))));
		return ok(baos);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return notFound(filename + " is Not Found!");
}
	
	public static Result uploadAPK() {
		MultipartFormData body = request().body().asMultipartFormData();
		  FilePart apkfile = body.getFile("files[]");
		  if (apkfile != null) {
			  String tmpPath = Play.application().path().getPath() + "/tmp/";
			  String path = Play.application().path().getPath() + "/upload/";
		    String fileTempName = String.valueOf(System.currentTimeMillis());
		    
		    String contentType = apkfile.getContentType(); 
		    
		    if(contentType == null || !contentType.startsWith("application/")){
		    	return ok("error:not apk file");
		    }
		    
		    File file = apkfile.getFile();
		    try {
		    	File tempFile = new File(tmpPath + fileTempName);
				IOUtils.copy(new FileInputStream(file), new FileOutputStream(tempFile));
				
				ApkReader ar = null;
				try {
//		            AndroidApk apk = new AndroidApk(tempFile);
					ar = new ApkReader();
					ApkInfo ai = new ApkInfo();
					int res = ar.read(tempFile.getAbsolutePath(), ai);
					
					//successfully to parse apk
					if(res == 0){
		            final String apkPkg = ai.packageName;
		            
		            AppModel am = AppModel.findByPkg(apkPkg);
		          //judge author is the current user
		            if(am == null || (am.author != null && Secured.isOwnerOf(am.author.name))){
		            	boolean flag = false;
		            	if(am == null){
		            		am = new AppModel();
		            		flag = true;
		            	}
		            	am.appname = ai.label;
		            	am.appVersion = ai.versionName;
		            	am.appVersionCode = ai.versionCode;
		            	am.author = Secured.getCurrentUser();
		            	am.createdAt = new Date();
		            	am.targetSdkVersion = ai.targetSdkVersion;
		            	am.minSdkVersion = ai.minSdkVersion;
		            	am.packageName = apkPkg;
		            	
		            	String apkName = am.packageName + "-" + am.appVersionCode + ".apk";
		            	am.downurl = routes.Developer.downloadApk(apkName).absoluteURL(request());
		            	am.iconUrl = saveIcon(am.packageName + "-" + am.appVersionCode + ".png", ai.iconFileName, path);
		            	am.iconUrl = routes.Developer.showImage(am.iconUrl).absoluteURL(request()); 
		            	
		            	if(flag){
		            		am.save();
		            	}else{
		            		am.update();
		            	}
		            	FileUtils.copyFile(tempFile, new File(path + apkName));
		            	FileUtils.forceDelete(tempFile);
		            }else{
		            	//TODO:return no permission to update other's app
		            	
		            }
		            
//		            System.out.println("  .appVersion     = " + apk.getAppVersion());
//		            System.out.println("  .appVersionCode = " + apk.getAppVersionCode());
					}
		        } catch(Throwable t) {
		            t.printStackTrace(System.err);
		        }finally{
		        	if(ar != null){
		        		ar.cleanup();
		        	}
		        }
				
				return redirect(routes.Developer.index());
//				return redirect(routes.Courses.showImage(fileName));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    
		    return ok("error:Writting file error");
		  } else {
		    flash("error", "Missing file");
		    return ok("error:Missing file");    
		  }
	}
	
	private static String saveIcon(String fileName, List<String> paths, String desPath){
		if(paths != null && paths.size() > 0){
			String path = paths.get(0);
			for(String tmp : paths){
				if(tmp.contains("hdpi")){
					path = tmp;
					break;
				}
			}
    	try {
    		desPath += fileName;
    		FileUtils.copyFile(new File(path), new File(desPath));
			return fileName;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
    	
    	return null;
	}

	public static Result deleteApp(Long app) {
		if(Secured.isOwnerOf(app)){
			AppModel.find.ref(app).delete();
			return redirect(routes.Developer.index());
		}else{
			return forbidden();
		}
    }
}

