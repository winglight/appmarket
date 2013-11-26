
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object register extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Application.Register],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(form: Form[Application.Register]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.36*/("""
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Register - Ultimate Black APP Market</title>
<link rel="stylesheet" href="""),_display_(Seq[Any](/*9.30*/routes/*9.36*/.Assets.at("css/style.default.css"))),format.raw/*9.71*/(""" type="text/css" />
</head>

<body class="loginpage">

	<div class="loginbox">
    	<div class="loginboxinner">
        	
            <div class="logo">
            	<h1><span>Ultimate Black APP Market</span></h1>
            </div><!--logo-->
            
            <br clear="all" /><br />
            
			"""),_display_(Seq[Any](/*23.5*/helper/*23.11*/.form(routes.Application.register(), 'id -> "login")/*23.63*/ {_display_(Seq[Any](format.raw/*23.65*/("""
					 
					 """),_display_(Seq[Any](/*25.8*/if(form.hasGlobalErrors)/*25.32*/ {_display_(Seq[Any](format.raw/*25.34*/(""" 
                <p class="error">
                <div class="loginmsg">
                    """),_display_(Seq[Any](/*28.22*/form/*28.26*/.globalError.message)),format.raw/*28.46*/("""
                    </div>
                </p>
            """)))})),format.raw/*31.14*/("""
                <div class="username">
                	<div class="usernameinner">
                    	<input type="text" name="username" id="username" value=""""),_display_(Seq[Any](/*34.79*/form("username")/*34.95*/.value)),format.raw/*34.101*/(""""  placeholder="User Name"/>
                    </div>
                </div>
                
                <div class="email">
                	<div class="emailinner">
                    	<input type="text" name="email" id="email" value=""""),_display_(Seq[Any](/*40.73*/form("email")/*40.86*/.value)),format.raw/*40.92*/("""" placeholder="Email"/>
                    </div>
                </div>
                
                <div class="password">
                	<div class="passwordinner">
                    	<input type="password" name="password" id="password"  placeholder="Password"/>
                    </div>
                </div>
                
                <div class="password">
                	<div class="passwordinner">
                    	<input type="password" name="twicePassword" id="twicePassword" placeholder="Password Twice" />
                    </div>
                </div>
                
                <button >Sign Up</button>
                <div align="right">
                <p>
                <a href=""""),_display_(Seq[Any](/*59.27*/routes/*59.33*/.Application.login())),format.raw/*59.53*/(""""><H2>LOG IN
                </H2></a></p>
                </div>
                
                <div class="keep"><input type="checkbox" /> Keep me logged in</div>
            
            """)))})),format.raw/*65.14*/("""
            
        </div><!--loginboxinner-->
    </div><!--loginbox-->

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*71.37*/routes/*71.43*/.Assets.at("js/plugins/jquery-ui-1.8.16.custom.min.js"))),format.raw/*71.98*/("""></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*72.37*/routes/*72.43*/.Assets.at("js/vendor/jquery.ui.widget.js"))),format.raw/*72.86*/("""></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*73.37*/routes/*73.43*/.Assets.at("js/plugins/jquery.cookie.js"))),format.raw/*73.84*/("""></script>
<script type="text/javascript" src=""""),_display_(Seq[Any](/*74.38*/routes/*74.44*/.Assets.at("js/custom/general.js"))),format.raw/*74.78*/(""""></script>
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="""),_display_(Seq[Any](/*76.49*/routes/*76.55*/.Assets.at("css/style.ie9.css"))),format.raw/*76.86*/("""/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="""),_display_(Seq[Any](/*79.49*/routes/*79.55*/.Assets.at("css/style.ie8.css"))),format.raw/*79.86*/("""/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->

</body>

</html>
"""))}
    }
    
    def render(form:Form[Application.Register]): play.api.templates.HtmlFormat.Appendable = apply(form)
    
    def f:((Form[Application.Register]) => play.api.templates.HtmlFormat.Appendable) = (form) => apply(form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Nov 25 17:08:38 CST 2013
                    SOURCE: D:/work/play-2.2.1/appmarket/app/views/register.scala.html
                    HASH: b380fbc755893c81f75bfcd96bd8aaeb7db0148a
                    MATRIX: 797->1|925->35|1367->442|1381->448|1437->483|1797->808|1812->814|1873->866|1913->868|1965->885|1998->909|2038->911|2173->1010|2186->1014|2228->1034|2325->1099|2527->1265|2552->1281|2581->1287|2869->1539|2891->1552|2919->1558|3707->2310|3722->2316|3764->2336|3995->2535|4233->2737|4248->2743|4325->2798|4409->2846|4424->2852|4489->2895|4573->2943|4588->2949|4651->2990|4736->3039|4751->3045|4807->3079|4920->3156|4935->3162|4988->3193|5106->3275|5121->3281|5174->3312
                    LINES: 26->1|29->1|37->9|37->9|37->9|51->23|51->23|51->23|51->23|53->25|53->25|53->25|56->28|56->28|56->28|59->31|62->34|62->34|62->34|68->40|68->40|68->40|87->59|87->59|87->59|93->65|99->71|99->71|99->71|100->72|100->72|100->72|101->73|101->73|101->73|102->74|102->74|102->74|104->76|104->76|104->76|107->79|107->79|107->79
                    -- GENERATED --
                */
            