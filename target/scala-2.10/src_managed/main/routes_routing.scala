// @SOURCE:D:/work/play-2.2.1/appmarket/conf/routes
// @HASH:96e907a6bea43355685d50045aaa94e4e5612700
// @DATE:Mon Nov 25 17:41:23 CST 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Application_login1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:10
private[this] lazy val controllers_Application_authenticate2 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("login"))))
        

// @LINE:11
private[this] lazy val controllers_Application_registerPage3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("register"))))
        

// @LINE:12
private[this] lazy val controllers_Application_register4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("register"))))
        

// @LINE:13
private[this] lazy val controllers_Application_logout5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("logout"))))
        

// @LINE:16
private[this] lazy val controllers_Developer_index6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("developer/index"))))
        

// @LINE:17
private[this] lazy val controllers_Developer_getApps7 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("developer/apps"))))
        

// @LINE:18
private[this] lazy val controllers_Developer_deleteApp8 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("developer/"),DynamicPart("app", """[^/]+""",true),StaticPart("/delete"))))
        

// @LINE:19
private[this] lazy val controllers_Developer_uploadAPK9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("developer/uploadApk"))))
        

// @LINE:20
private[this] lazy val controllers_Developer_downloadApk10 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("developer/downloadApk/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:21
private[this] lazy val controllers_Developer_showImage11 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("developer/downloadIcon/"),DynamicPart("name", """[^/]+""",true))))
        

// @LINE:24
private[this] lazy val controllers_AppsShow_getHotapps12 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("apps/hots/"),DynamicPart("page", """[^/]+""",true))))
        

// @LINE:27
private[this] lazy val controllers_Assets_at13 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.login()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """login""","""controllers.Application.authenticate()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """register""","""controllers.Application.registerPage()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """register""","""controllers.Application.register()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """logout""","""controllers.Application.logout()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """developer/index""","""controllers.Developer.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """developer/apps""","""controllers.Developer.getApps()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """developer/$app<[^/]+>/delete""","""controllers.Developer.deleteApp(app:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """developer/uploadApk""","""controllers.Developer.uploadAPK()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """developer/downloadApk/$name<[^/]+>""","""controllers.Developer.downloadApk(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """developer/downloadIcon/$name<[^/]+>""","""controllers.Developer.showImage(name:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """apps/hots/$page<[^/]+>""","""controllers.AppsShow.getHotapps(page:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Application_login1(params) => {
   call { 
        invokeHandler(controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Nil,"GET", """ Authentication""", Routes.prefix + """login"""))
   }
}
        

// @LINE:10
case controllers_Application_authenticate2(params) => {
   call { 
        invokeHandler(controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Nil,"POST", """""", Routes.prefix + """login"""))
   }
}
        

// @LINE:11
case controllers_Application_registerPage3(params) => {
   call { 
        invokeHandler(controllers.Application.registerPage(), HandlerDef(this, "controllers.Application", "registerPage", Nil,"GET", """""", Routes.prefix + """register"""))
   }
}
        

// @LINE:12
case controllers_Application_register4(params) => {
   call { 
        invokeHandler(controllers.Application.register(), HandlerDef(this, "controllers.Application", "register", Nil,"POST", """""", Routes.prefix + """register"""))
   }
}
        

// @LINE:13
case controllers_Application_logout5(params) => {
   call { 
        invokeHandler(controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Nil,"GET", """""", Routes.prefix + """logout"""))
   }
}
        

// @LINE:16
case controllers_Developer_index6(params) => {
   call { 
        invokeHandler(controllers.Developer.index(), HandlerDef(this, "controllers.Developer", "index", Nil,"GET", """Developer""", Routes.prefix + """developer/index"""))
   }
}
        

// @LINE:17
case controllers_Developer_getApps7(params) => {
   call { 
        invokeHandler(controllers.Developer.getApps(), HandlerDef(this, "controllers.Developer", "getApps", Nil,"GET", """""", Routes.prefix + """developer/apps"""))
   }
}
        

// @LINE:18
case controllers_Developer_deleteApp8(params) => {
   call(params.fromPath[Long]("app", None)) { (app) =>
        invokeHandler(controllers.Developer.deleteApp(app), HandlerDef(this, "controllers.Developer", "deleteApp", Seq(classOf[Long]),"DELETE", """""", Routes.prefix + """developer/$app<[^/]+>/delete"""))
   }
}
        

// @LINE:19
case controllers_Developer_uploadAPK9(params) => {
   call { 
        invokeHandler(controllers.Developer.uploadAPK(), HandlerDef(this, "controllers.Developer", "uploadAPK", Nil,"POST", """""", Routes.prefix + """developer/uploadApk"""))
   }
}
        

// @LINE:20
case controllers_Developer_downloadApk10(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.Developer.downloadApk(name), HandlerDef(this, "controllers.Developer", "downloadApk", Seq(classOf[String]),"GET", """""", Routes.prefix + """developer/downloadApk/$name<[^/]+>"""))
   }
}
        

// @LINE:21
case controllers_Developer_showImage11(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.Developer.showImage(name), HandlerDef(this, "controllers.Developer", "showImage", Seq(classOf[String]),"GET", """""", Routes.prefix + """developer/downloadIcon/$name<[^/]+>"""))
   }
}
        

// @LINE:24
case controllers_AppsShow_getHotapps12(params) => {
   call(params.fromPath[Long]("page", None)) { (page) =>
        invokeHandler(controllers.AppsShow.getHotapps(page), HandlerDef(this, "controllers.AppsShow", "getHotapps", Seq(classOf[Long]),"GET", """APPS""", Routes.prefix + """apps/hots/$page<[^/]+>"""))
   }
}
        

// @LINE:27
case controllers_Assets_at13(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}

}
     