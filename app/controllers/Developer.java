package controllers;

import play.mvc.*;
import play.mvc.Http.Context;
import static play.data.Form.*;

import java.util.*;

import models.*;

import util.Constants;
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

}

