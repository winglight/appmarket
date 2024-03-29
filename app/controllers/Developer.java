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
		String name = Context.current().session()
				.get(Constants.SESSION_USER_NAME);
		if(name != null){
			UserModel user = UserModel.findByloginName(name);
			return ok(developer.render(user));
		}else{
			return redirect(routes.Application.login());
		}
	}

	public static Result getApps() {
		MessageModel<List<AppModel>> mm = new MessageModel<List<AppModel>>();
		mm.setFlag(true);
		mm.setData(AppModel.findAppsByAuthor(Secured.getCurrentUser()));
		return ok(Json.toJson(mm));
	}

	public static Result uploadAPK(Long cid) {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart apkfile = body.getFile("files[]");
		if (apkfile != null) {
			String path = Play.application().path().getPath() + "/upload/";

			String contentType = apkfile.getContentType();

			if (contentType == null || !contentType.startsWith("application/")) {
				return ok(Json.toJson("error:not apk file"));
			}

			File file = apkfile.getFile();
				ApkReader ar = null;
				try {
					// AndroidApk apk = new AndroidApk(tempFile);
					ar = new ApkReader();
					ApkInfo ai = new ApkInfo();
					int res = ar.read(file.getAbsolutePath(), ai);

					// successfully to parse apk
					if (res == 0) {
						final String apkPkg = ai.packageName;

						AppModel am = AppModel.findByPkg(apkPkg);
						// judge author is the current user
						if (am == null
								|| (am.author != null && Secured
										.isOwnerOf(am.author.name))) {
							boolean flag = false;
							if (am == null) {
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

							String apkName = am.packageName + "-"
									+ am.appVersionCode + ".apk";
							am.downurl = routes.AppsShow.downloadApk(apkName)
									.absoluteURL(request());
							am.iconUrl = saveIcon(am.packageName + "-"
									+ am.appVersionCode + ".png",
									ai.iconFileName, path);
							am.iconUrl = routes.AppsShow.showImage(am.iconUrl)
									.absoluteURL(request());
							
							//add category
							if(cid != null && cid > 0){
								am.category = CategoryModel.find.byId(cid);
							}

							if (flag) {
								am.save();
							} else {
								am.update();
							}
							FileUtils.copyFile(file, new File(path
									+ apkName));
							return ok(Json.toJson(am));
						} else {
							// TODO:return no permission to update other's app
							return ok(Json.toJson("error: No permission to update other's APP: " + am.appname));
						}

						// System.out.println("  .appVersion     = " +
						// apk.getAppVersion());
						// System.out.println("  .appVersionCode = " +
						// apk.getAppVersionCode());
					}
				} catch (Throwable t) {
					t.printStackTrace(System.err);
				} finally {
					if (ar != null) {
						ar.cleanup();
					}
				}

				
				// return redirect(routes.Courses.showImage(fileName));

		}
			return ok(Json.toJson("error:Missing file"));
	}

	private static String saveIcon(String fileName, List<String> paths,
			String desPath) {
		if (paths != null && paths.size() > 0) {
			String path = paths.get(0);
			for (String tmp : paths) {
				if (tmp.contains("hdpi")) {
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
		if (Secured.isOwnerOf(app)) {
			AppModel.find.ref(app).delete();
			return ok(Constants.RETURN_SUCCESS);
		} else {
			return ok(Constants.RETURN_FORBIDDEN);
		}
	}
}
