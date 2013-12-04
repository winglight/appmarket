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
import models.status.UserRole;

import util.Constants;
import util.Utils;
import views.html.*;

/**
 * Manage common user and developers.
 */
@Security.Authenticated(Secured.class)
public class Administrator extends Controller {

	/**
	 * Display the dashboard.
	 */
	public static Result index() {
		String name = Context.current().session()
				.get(Constants.SESSION_USER_NAME);
		if(name != null){
			UserModel user = UserModel.findByloginName(name);
			if(user.userRole == UserRole.ADMIN){
				return ok(administrator.render(user));
			}
		}
			return redirect(routes.Application.login());
	}

}
