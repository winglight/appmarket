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

public class AppsShow extends Controller {
  
    public static Result getHotapps(Long page) {
		MessageModel<List<AppModel>> mm = new MessageModel<List<AppModel>>();
		mm.setFlag(true);
		mm.setData(AppModel.findAppsByHots(page.intValue()));
		return ok(Json.toJson(mm));
	}
    
}

