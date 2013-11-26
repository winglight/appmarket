// @SOURCE:D:/work/play-2.2.1/appmarket/conf/routes
// @HASH:96e907a6bea43355685d50045aaa94e4e5612700
// @DATE:Mon Nov 25 17:41:23 CST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers {

// @LINE:24
class ReverseAppsShow {
    

// @LINE:24
def getHotapps(page:Long): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "apps/hots/" + implicitly[PathBindable[Long]].unbind("page", page))
}
                                                
    
}
                          

// @LINE:27
class ReverseAssets {
    

// @LINE:27
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          

// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
class ReverseDeveloper {
    

// @LINE:17
def getApps(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "developer/apps")
}
                                                

// @LINE:19
def uploadAPK(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "developer/uploadApk")
}
                                                

// @LINE:21
def showImage(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "developer/downloadIcon/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)))
}
                                                

// @LINE:18
def deleteApp(app:Long): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "developer/" + implicitly[PathBindable[Long]].unbind("app", app) + "/delete")
}
                                                

// @LINE:16
def index(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "developer/index")
}
                                                

// @LINE:20
def downloadApk(name:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "developer/downloadApk/" + implicitly[PathBindable[String]].unbind("name", dynamicString(name)))
}
                                                
    
}
                          

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def registerPage(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "register")
}
                                                

// @LINE:13
def logout(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "logout")
}
                                                

// @LINE:12
def register(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "register")
}
                                                

// @LINE:10
def authenticate(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "login")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:9
def login(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "login")
}
                                                
    
}
                          
}
                  


// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:24
class ReverseAppsShow {
    

// @LINE:24
def getHotapps : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.AppsShow.getHotapps",
   """
      function(page) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "apps/hots/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("page", page)})
      }
   """
)
                        
    
}
              

// @LINE:27
class ReverseAssets {
    

// @LINE:27
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              

// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
class ReverseDeveloper {
    

// @LINE:17
def getApps : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Developer.getApps",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "developer/apps"})
      }
   """
)
                        

// @LINE:19
def uploadAPK : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Developer.uploadAPK",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "developer/uploadApk"})
      }
   """
)
                        

// @LINE:21
def showImage : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Developer.showImage",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "developer/downloadIcon/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        

// @LINE:18
def deleteApp : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Developer.deleteApp",
   """
      function(app) {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "developer/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("app", app) + "/delete"})
      }
   """
)
                        

// @LINE:16
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Developer.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "developer/index"})
      }
   """
)
                        

// @LINE:20
def downloadApk : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Developer.downloadApk",
   """
      function(name) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "developer/downloadApk/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("name", encodeURIComponent(name))})
      }
   """
)
                        
    
}
              

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def registerPage : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.registerPage",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
      }
   """
)
                        

// @LINE:13
def logout : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.logout",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "logout"})
      }
   """
)
                        

// @LINE:12
def register : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.register",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "register"})
      }
   """
)
                        

// @LINE:10
def authenticate : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.authenticate",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:9
def login : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.login",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "login"})
      }
   """
)
                        
    
}
              
}
        


// @LINE:27
// @LINE:24
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
package controllers.ref {


// @LINE:24
class ReverseAppsShow {
    

// @LINE:24
def getHotapps(page:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.AppsShow.getHotapps(page), HandlerDef(this, "controllers.AppsShow", "getHotapps", Seq(classOf[Long]), "GET", """APPS""", _prefix + """apps/hots/$page<[^/]+>""")
)
                      
    
}
                          

// @LINE:27
class ReverseAssets {
    

// @LINE:27
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          

// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:17
// @LINE:16
class ReverseDeveloper {
    

// @LINE:17
def getApps(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Developer.getApps(), HandlerDef(this, "controllers.Developer", "getApps", Seq(), "GET", """""", _prefix + """developer/apps""")
)
                      

// @LINE:19
def uploadAPK(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Developer.uploadAPK(), HandlerDef(this, "controllers.Developer", "uploadAPK", Seq(), "POST", """""", _prefix + """developer/uploadApk""")
)
                      

// @LINE:21
def showImage(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Developer.showImage(name), HandlerDef(this, "controllers.Developer", "showImage", Seq(classOf[String]), "GET", """""", _prefix + """developer/downloadIcon/$name<[^/]+>""")
)
                      

// @LINE:18
def deleteApp(app:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Developer.deleteApp(app), HandlerDef(this, "controllers.Developer", "deleteApp", Seq(classOf[Long]), "DELETE", """""", _prefix + """developer/$app<[^/]+>/delete""")
)
                      

// @LINE:16
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Developer.index(), HandlerDef(this, "controllers.Developer", "index", Seq(), "GET", """Developer""", _prefix + """developer/index""")
)
                      

// @LINE:20
def downloadApk(name:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Developer.downloadApk(name), HandlerDef(this, "controllers.Developer", "downloadApk", Seq(classOf[String]), "GET", """""", _prefix + """developer/downloadApk/$name<[^/]+>""")
)
                      
    
}
                          

// @LINE:13
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:6
class ReverseApplication {
    

// @LINE:11
def registerPage(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.registerPage(), HandlerDef(this, "controllers.Application", "registerPage", Seq(), "GET", """""", _prefix + """register""")
)
                      

// @LINE:13
def logout(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.logout(), HandlerDef(this, "controllers.Application", "logout", Seq(), "GET", """""", _prefix + """logout""")
)
                      

// @LINE:12
def register(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.register(), HandlerDef(this, "controllers.Application", "register", Seq(), "POST", """""", _prefix + """register""")
)
                      

// @LINE:10
def authenticate(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.authenticate(), HandlerDef(this, "controllers.Application", "authenticate", Seq(), "POST", """""", _prefix + """login""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:9
def login(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.login(), HandlerDef(this, "controllers.Application", "login", Seq(), "GET", """ Authentication""", _prefix + """login""")
)
                      
    
}
                          
}
        
    