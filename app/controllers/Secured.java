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
    
}