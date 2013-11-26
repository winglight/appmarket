
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
object main extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template3[UserModel,String,Html,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(user: UserModel, title: String)(content: Html):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.49*/("""

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>"""),_display_(Seq[Any](/*9.9*/title)),format.raw/*9.14*/(""" - Ultimate Black APP Market</title>
<link rel="stylesheet" href=""""),_display_(Seq[Any](/*10.31*/routes/*10.37*/.Assets.at("css/style.default.css"))),format.raw/*10.72*/("""" type="text/css" />
</head>

<body class="withvernav">

<div class="bodywrapper">
    <div class="topheader">
        <div class="left">
            <h1 class="logo">ULTIMATE BLACK.<span>APP MARKET</span></h1>
            <span class="slogan">total free to download and upload</span>
            
            <div class="search">
            	<form action="#" method="post">
                	<input type="text" name="keyword" id="keyword" value="Enter keyword(s)" />
                    <button class="submitbutton"></button>
                </form>
            </div><!--search-->
            
            <br clear="all" />
            
        </div><!--left-->
        
        <div class="right">
        	"""),_display_(Seq[Any](/*33.11*/if(user != null)/*33.27*/{_display_(Seq[Any](format.raw/*33.28*/("""
            <div class="userinfo">
            	<img src=""""),_display_(Seq[Any](/*35.25*/routes/*35.31*/.Assets.at("images/thumbs/avatar.png"))),format.raw/*35.69*/("""" alt="" />
                <span>"""),_display_(Seq[Any](/*36.24*/user/*36.28*/.name)),format.raw/*36.33*/("""</span>
            </div><!--userinfo-->
            
            <div class="userinfodrop">            	
            	<div class="avatar">
                	<a href="#"><img src=""""),_display_(Seq[Any](/*41.41*/routes/*41.47*/.Assets.at("images/thumbs/avatarbig.png"))),format.raw/*41.88*/("""" alt="" /></a>
                    <div class="changetheme">
                    	Change theme: <br />
                    	<a class="default"></a>
                        <a class="blueline"></a>
                        <a class="greenline"></a>
                        <a class="contrast"></a>
                        <a class="custombg"></a>
                    </div>
                </div><!--avatar-->
				<div class="userdata">
                	<h4>"""),_display_(Seq[Any](/*52.23*/user/*52.27*/.name)),format.raw/*52.32*/("""</h4>
                    <span class="email">"""),_display_(Seq[Any](/*53.42*/user/*53.46*/.email)),format.raw/*53.52*/("""</span>
                    <ul>
                        <li><a href="accountsettings.html">Account Settings</a></li>
                        <li><a href="help.html">Help</a></li>
                        <li><a href="index.html">Sign Out</a></li>
                    </ul>
                </div><!--userdata-->
            </div><!--userinfodrop-->
            """)))}/*61.14*/else/*61.18*/{_display_(Seq[Any](format.raw/*61.19*/("""
            <a href=""""),_display_(Seq[Any](/*62.23*/routes/*62.29*/.Application.login())),format.raw/*62.49*/("""">
            	<button class="stdbtn btn_lime">Sign In</button>
            </a>
            """)))})),format.raw/*65.14*/("""
        </div><!--right-->
    </div><!--topheader-->
    
    
    <div class="header">
    	<ul class="headermenu">
        	<li><a href=""""),_display_(Seq[Any](/*72.24*/routes/*72.30*/.Application.index())),format.raw/*72.50*/(""""><span class="icon icon-flatscreen"></span>HOT APPS</a></li>
            <li><a href="manageblog.html"><span class="icon icon-pencil"></span>Categories</a></li>
            <li><a href="messages.html"><span class="icon icon-message"></span>Newest</a></li>
            <li><a href=""""),_display_(Seq[Any](/*75.27*/routes/*75.33*/.Developer.index())),format.raw/*75.51*/(""""><span class="icon icon-chart"></span>Upload</a></li>
        </ul>
        
        <div class="headerwidget">
        	<div class="earnings">
            	<div class="one_half">
                	<h4>DOWNLOADs</h4>
                    <h2>347,893</h2>
                </div><!--one_half-->
                <div class="one_half last alignright">
                	<h4>Total APPs</h4>
                    <h2>16532</h2>
                </div><!--one_half last-->
            </div><!--earnings-->
        </div><!--headerwidget-->
        
    </div><!--header-->
    
	"""),_display_(Seq[Any](/*93.3*/content)),format.raw/*93.10*/("""
<!--bodywrapper-->
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*96.37*/routes/*96.43*/.Assets.at("js/plugins/jquery-ui-1.8.16.custom.min.js"))),format.raw/*96.98*/("""></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*97.37*/routes/*97.43*/.Assets.at("js/vendor/jquery.ui.widget.js"))),format.raw/*97.86*/("""></script>
<script type="text/javascript" src="""),_display_(Seq[Any](/*98.37*/routes/*98.43*/.Assets.at("js/plugins/jquery.cookie.js"))),format.raw/*98.84*/("""></script>
<script type="text/javascript" src=""""),_display_(Seq[Any](/*99.38*/routes/*99.44*/.Assets.at("js/custom/general.js"))),format.raw/*99.78*/(""""></script>
<script type="text/javascript" src=""""),_display_(Seq[Any](/*100.38*/routes/*100.44*/.Assets.at("js/custom/productlist.js"))),format.raw/*100.82*/(""""></script>
<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.2.1/angular.min.js")"></script>
  
<!--[if IE 9]>
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*104.50*/routes/*104.56*/.Assets.at("css/style.ie9.css"))),format.raw/*104.87*/(""""/>
<![endif]-->
<!--[if IE 8]>
    <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*107.50*/routes/*107.56*/.Assets.at("css/style.ie8.css"))),format.raw/*107.87*/(""""/>
<![endif]-->
<!--[if lt IE 9]>
	<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
<![endif]-->

</body>

</html>

"""))}
    }
    
    def render(user:UserModel,title:String,content:Html): play.api.templates.HtmlFormat.Appendable = apply(user,title)(content)
    
    def f:((UserModel,String) => (Html) => play.api.templates.HtmlFormat.Appendable) = (user,title) => (content) => apply(user,title)(content)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Nov 25 17:08:38 CST 2013
                    SOURCE: D:/work/play-2.2.1/appmarket/app/views/main.scala.html
                    HASH: cd216487d4c178ab09a8daeea7ea37dfaeb54185
                    MATRIX: 788->1|929->48|1298->383|1324->388|1428->456|1443->462|1500->497|2272->1233|2297->1249|2336->1250|2434->1312|2449->1318|2509->1356|2581->1392|2594->1396|2621->1401|2843->1587|2858->1593|2921->1634|3426->2103|3439->2107|3466->2112|3550->2160|3563->2164|3591->2170|3980->2540|3993->2544|4032->2545|4092->2569|4107->2575|4149->2595|4279->2693|4464->2842|4479->2848|4521->2868|4843->3154|4858->3160|4898->3178|5521->3766|5550->3773|5729->3916|5744->3922|5821->3977|5905->4025|5920->4031|5985->4074|6069->4122|6084->4128|6147->4169|6232->4218|6247->4224|6303->4258|6390->4308|6406->4314|6467->4352|6681->4529|6697->4535|6751->4566|6872->4650|6888->4656|6942->4687
                    LINES: 26->1|29->1|37->9|37->9|38->10|38->10|38->10|61->33|61->33|61->33|63->35|63->35|63->35|64->36|64->36|64->36|69->41|69->41|69->41|80->52|80->52|80->52|81->53|81->53|81->53|89->61|89->61|89->61|90->62|90->62|90->62|93->65|100->72|100->72|100->72|103->75|103->75|103->75|121->93|121->93|124->96|124->96|124->96|125->97|125->97|125->97|126->98|126->98|126->98|127->99|127->99|127->99|128->100|128->100|128->100|132->104|132->104|132->104|135->107|135->107|135->107
                    -- GENERATED --
                */
            