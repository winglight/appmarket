package controllers;

import play.Play;
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

import apk.AndroidApk;

import models.*;

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
		    String fileName = apkfile.getFilename();
		    String fileTempName = String.valueOf(System.currentTimeMillis());
		    
		    String contentType = apkfile.getContentType(); 
		    
		    if(contentType == null || !contentType.startsWith("application/")){
		    	return ok("error:not apk file");
		    }
		    
		    File file = apkfile.getFile();
		    try {
		    	File tempFile = new File(tmpPath + fileTempName);
				IOUtils.copy(new FileInputStream(file), new FileOutputStream(tempFile));
				try {
		            AndroidApk apk = new AndroidApk(tempFile);
		            String apkPkg = apk.getPackageName();
		            
		            AppModel am = AppModel.findByPkg(apkPkg);
		          //judge author is the current user
		            if(am == null || (am.author != null && Secured.isOwnerOf(am.author.name))){
		            	boolean flag = false;
		            	if(am == null){
		            		am = new AppModel();
		            		flag = true;
		            	}
		            	am.appname = apk.getAppname();
		            	am.appVersion = apk.getAppVersion();
		            	am.appVersionCode = apk.getAppVersionCode();
		            	am.author = Secured.getCurrentUser();
		            	am.createdAt = new Date();
		            	am.maxSdkVersion = apk.getMaxSdkVersion();
		            	am.minSdkVersion = apk.getMinSdkVersion();
		            	am.packageName = apk.getPackageName();
		            	am.downurl = am.packageName + "-" + am.appVersionCode + ".apk";
		            	am.iconUrl = saveIcon(apk, path);
		            	am.targetSdkVersion = apk.getTargetSdkVersion();
		            	
		            	if(flag){
		            		am.save();
		            	}else{
		            		am.update();
		            	}
		            	FileUtils.moveFile(tempFile, new File(path + am.downurl));
		            }else{
		            	//TODO:return no permission to update other's app
		            	
		            }
		            
		            System.out.println("  .appVersion     = " + apk.getAppVersion());
		            System.out.println("  .appVersionCode = " + apk.getAppVersionCode());
		        } catch(Throwable t) {
		            t.printStackTrace(System.err);
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
	
	private static String saveIcon(AndroidApk apk, String path){
		byte[] iconBytes = apk.getIconBytes();
    	String iconUrl = apk.getPackageName() + "-" + apk.getAppVersionCode() + ".png";

    	try {
			IOUtils.write(iconBytes, new FileOutputStream(new File(path + iconUrl)));
			return iconUrl;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

