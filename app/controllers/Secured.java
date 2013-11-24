package controllers;

import play.mvc.*;
import play.mvc.Http.*;

import models.*;
import util.*;

public class Secured extends Security.Authenticator {
    
    @Override
    public String getUsername(Context ctx) {
        return ctx.session().get(Constants.SESSION_USER_NAME);
    }
    
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Application.login());
    }
    
    public static boolean isOwnerOf(String userName) {
        return Context.current().session().get(Constants.SESSION_USER_NAME).equals(userName);
    }
    
    public static boolean isOwnerOf(Long app) {
        return AppModel.isOwner(
                app,
                Context.current().request().username()
            );
    }
    
    public static UserModel getCurrentUser() {
        String name = Context.current().session().get(Constants.SESSION_USER_NAME);
        return UserModel.findByloginName(name);
    }
}