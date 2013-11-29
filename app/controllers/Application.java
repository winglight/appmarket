package controllers;

import static play.data.Form.form;

import java.util.Date;

import models.UserModel;
import models.status.UserRole;
import models.status.UserStatus;

import play.*;
import play.data.Form;
import play.mvc.*;
import play.mvc.Http.Context;

import util.Constants;
import views.html.*;

public class Application extends Controller {

	public static class Login {

		public String username;
		public String password;

		public String validate() {
			if (UserModel.authenticate(username, password) == null) {
				return "Invalid user or password";
			}
			return null;
		}

	}
	
	public static class Register {

		public String username;
		public String email;
		public String password;
		public String twicePassword;

		public String validate() {
			if(password == null || !password.equals(twicePassword)){
				return "password is not compatible";
			}
			if (!UserModel.verifyUser(username)) {
				return "Invalid user name, " + username + " was already registered";
			}
			if (!UserModel.verifyEmail(email)) {
				return "Invalid email address";
			}
			return null;
		}

	}
	
    public static Result login() {
        return ok(login.render(form(Login.class)));
    }
    
    public static Result registerPage() {
        return ok(register.render(form(Register.class)));
    }
    
    /**
	 * Logout and clean the session.
	 */
	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.Application.login());
	}

    /**
	 * Handle login form submission.
	 */
	public static Result authenticate() {
			Form<Login> loginForm = form(Login.class).bindFromRequest();
			if (loginForm.hasErrors()) {
				return badRequest(login.render(loginForm));
			} else {
				session(Constants.SESSION_USER_NAME, loginForm.get().username);
				return redirect(routes.Application.index());
			}
	}
	
	public static Result register() {
		Form<Register> registerForm = form(Register.class).bindFromRequest();
		if (registerForm.hasErrors()) {
			return badRequest(register.render(registerForm));
		} else {
			Register register = registerForm.get();
			UserModel um = new UserModel();
			um.name = register.username;
			um.email = register.email;
			um.password = register.password;
			um.createdAt = new Date();
			um.status = UserStatus.Active;
			um.userRole = UserRole.DEVELOPER;
			//save user
			um.save();
			
			session(Constants.SESSION_USER_NAME, register.username);
			return redirect(routes.Application.index());
		}
}
	
    public static Result index() {
    	String name = Context.current().session().get(Constants.SESSION_USER_NAME);
    	UserModel user = UserModel.findByloginName(name);
        return ok(hotapps.render(user));
    }
    
    public static Result categoryApps() {
    	String name = Context.current().session().get(Constants.SESSION_USER_NAME);
    	UserModel user = UserModel.findByloginName(name);
        return ok(categoryapps.render(user));
    }
    
    public static Result newestApps() {
    	String name = Context.current().session().get(Constants.SESSION_USER_NAME);
    	UserModel user = UserModel.findByloginName(name);
        return ok(newestapps.render(user));
    }

}
