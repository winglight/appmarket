
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
object login extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[Form[Application.Login],play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(form: Form[Application.Login]):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.33*/("""
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Login - Ultimate Black APP Market</title>
<link rel="stylesheet" href="""),_display_(Seq[Any](/*9.30*/routes/*9.36*/.Assets.at("css/style.default.css"))),format.raw/*9.71*/(""" type="text/css" />
</head>

<body class="loginpage">

	<div class="loginbox">
    	<div class="loginboxinner">
        	
            <div class="logo">
            	<h1><span>Ultimate Black APP Market</span></h1>
            </div><!--logo-->
            
            <br clear="all" /><br />
            
			"""),_display_(Seq[Any](/*23.5*/helper/*23.11*/.form(routes.Application.authenticate(), 'id -> "login")/*23.67*/ {_display_(Seq[Any](format.raw/*23.69*/("""
					 
					 """),_display_(Seq[Any](/*25.8*/if(form.hasGlobalErrors)/*25.32*/ {_display_(Seq[Any](format.raw/*25.34*/(""" 
                <p class="error">
                <div class="loginmsg">
                    """),_display_(Seq[Any](/*28.22*/form/*28.26*/.globalError.message)),format.raw/*28.46*/("""
                    </div>
                </p>
            """)))})),format.raw/*31.14*/("""
                <div class="username">
                	<div class="usernameinner">
                    	<input type="text" name="username" id="username" value=""""),_display_(Seq[Any](/*34.79*/form("username")/*34.95*/.value)),format.raw/*34.101*/("""" placeholder="User Name"/>
                    </div>
                </div>
                
                <div class="password">
                	<div class="passwordinner">
                    	<input type="password" name="password" id="password" placeholder="Password" />
                    </div>
                </div>
                <button>Sign In</button>
                <div align="right">
                <p>
                <a href=""""),_display_(Seq[Any](/*46.27*/routes/*46.33*/.Application.registerPage())),format.raw/*46.60*/(""""><H2>
                REGISTER
                </H2></a></p>
                </div>
                
                <div class="keep"><input type="checkbox" /> Keep me logged in</div>
            
            """)))})),format.raw/*53.14*/("""
            
        </div><!--loginboxinner-->
    </div><!--loginbox-->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*58.37*/routes/*58.43*/.Assets.at("js/plugins/jquery-ui-1.8.16.custom.min.js"))),format.raw/*58.98*/("""></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*59.37*/routes/*59.43*/.Assets.at("js/vendor/jquery.ui.widget.js"))),format.raw/*59.86*/("""></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*60.37*/routes/*60.43*/.Assets.at("js/plugins/jquery.cookie.js"))),format.raw/*60.84*/("""></script>
<script type="text/javascript" src=""""),_display_(Seq[Any](/*61.38*/routes/*61.44*/.Assets.at("js/custom/general.js"))),format.raw/*61.78*/(""""></script>
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="""),_display_(Seq[Any](/*63.49*/routes/*63.55*/.Assets.at("css/style.ie9.css"))),format.raw/*63.86*/("""/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="""),_display_(Seq[Any](/*66.49*/routes/*66.55*/.Assets.at("css/style.ie8.css"))),format.raw/*66.86*/("""/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->

</body>

</html>
"""))}
    }
    
    def render(form:Form[Application.Login]): play.api.templates.HtmlFormat.Appendable = apply(form)
    
    def f:((Form[Application.Login]) => play.api.templates.HtmlFormat.Appendable) = (form) => apply(form)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Nov 25 17:08:38 CST 2013
                    SOURCE: D:/work/play-2.2.1/appmarket/app/views/login.scala.html
                    HASH: 5dd0fdd54e3a92607d9d182cc3ac1c9162b2026a
                    MATRIX: 791->1|916->32|1355->436|1369->442|1425->477|1785->802|1800->808|1865->864|1905->866|1957->883|1990->907|2030->909|2165->1008|2178->1012|2220->1032|2317->1097|2519->1263|2544->1279|2573->1285|3073->1749|3088->1755|3137->1782|3388->2001|3624->2201|3639->2207|3716->2262|3800->2310|3815->2316|3880->2359|3964->2407|3979->2413|4042->2454|4127->2503|4142->2509|4198->2543|4311->2620|4326->2626|4379->2657|4497->2739|4512->2745|4565->2776
                    LINES: 26->1|29->1|37->9|37->9|37->9|51->23|51->23|51->23|51->23|53->25|53->25|53->25|56->28|56->28|56->28|59->31|62->34|62->34|62->34|74->46|74->46|74->46|81->53|86->58|86->58|86->58|87->59|87->59|87->59|88->60|88->60|88->60|89->61|89->61|89->61|91->63|91->63|91->63|94->66|94->66|94->66
                    -- GENERATED --
                */
            