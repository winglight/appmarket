
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
object hotapps extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[UserModel,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(user: UserModel):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main(user, "Hot APPs")/*3.24*/ {_display_(Seq[Any](format.raw/*3.26*/("""

    <div class="centercontent" ng-app>
    
        <div class="pageheader notab">
            <h1 class="pagetitle">Hot APPs</h1>
            <span class="pagedesc">hottest apps in this world.</span>
        </div><!--pageheader-->
        
        <div id="contentwrapper" class="contentwrapper nopadding" ng-controller='HotController'>
         
         <div class="prodwrapper">   
         
        	<div class="prodhead">
            	<ul class="prodhead_menu">
                    <li><a ng-href="prevPage()" class="prev """),format.raw/*18.61*/("""{"""),format.raw/*18.62*/("""prev_disabled: page.start==1"""),format.raw/*18.90*/("""}"""),format.raw/*18.91*/(""""></a></li>
                    <li><a ng-href="nextPage()" class="next """),format.raw/*19.61*/("""{"""),format.raw/*19.62*/("""next_disabled: page.end==page.total"""),format.raw/*19.97*/("""}"""),format.raw/*19.98*/(""""></a></li>
                    <li class="right"><span class="pagenuminfo">Showing """),format.raw/*20.73*/("""{"""),format.raw/*20.74*/("""{"""),format.raw/*20.75*/("""page.start"""),format.raw/*20.85*/("""}"""),format.raw/*20.86*/("""}"""),format.raw/*20.87*/(""" - """),format.raw/*20.90*/("""{"""),format.raw/*20.91*/("""{"""),format.raw/*20.92*/("""page.end"""),format.raw/*20.100*/("""}"""),format.raw/*20.101*/("""}"""),format.raw/*20.102*/(""" of """),format.raw/*20.106*/("""{"""),format.raw/*20.107*/("""{"""),format.raw/*20.108*/("""page.total"""),format.raw/*20.118*/("""}"""),format.raw/*20.119*/("""}"""),format.raw/*20.120*/("""</span></li>
                </ul>
                <span class="clearall"></span>
            </div><!--prodhead-->
         
         	<div class="prodcontent">
            
                <ul class="prodlist">
                    <li class="one_third" ng-repeat='app in hotapps'>
                        <div class="thumb"><a ng-href=""""),format.raw/*29.56*/("""{"""),format.raw/*29.57*/("""{"""),format.raw/*29.58*/("""app.downurl"""),format.raw/*29.69*/("""}"""),format.raw/*29.70*/("""}"""),format.raw/*29.71*/(""""><img ng-src=""""),format.raw/*29.86*/("""{"""),format.raw/*29.87*/("""{"""),format.raw/*29.88*/("""app.iconurl"""),format.raw/*29.99*/("""}"""),format.raw/*29.100*/("""}"""),format.raw/*29.101*/("""" alt="" /></a></div>
                        <div class="content" ng-href=""""),format.raw/*30.55*/("""{"""),format.raw/*30.56*/("""{"""),format.raw/*30.57*/("""app.downurl"""),format.raw/*30.68*/("""}"""),format.raw/*30.69*/("""}"""),format.raw/*30.70*/("""">
                        	<div class="contentinner">
                            	<div>
                            		<span class="price">"""),format.raw/*33.51*/("""{"""),format.raw/*33.52*/("""{"""),format.raw/*33.53*/("""app.version"""),format.raw/*33.64*/("""}"""),format.raw/*33.65*/("""}"""),format.raw/*33.66*/("""</span>
                            		<a href="#" class="title">"""),format.raw/*34.57*/("""{"""),format.raw/*34.58*/("""{"""),format.raw/*34.59*/("""app.name"""),format.raw/*34.67*/("""}"""),format.raw/*34.68*/("""}"""),format.raw/*34.69*/("""</a>
                                </div>
                                <div class="by">By: <a href="#">"""),format.raw/*36.65*/("""{"""),format.raw/*36.66*/("""{"""),format.raw/*36.67*/("""app.author"""),format.raw/*36.77*/("""}"""),format.raw/*36.78*/("""}"""),format.raw/*36.79*/("""</a></div>
                                <p class="desc"><img ng-src="http://api.qrserver.com/v1/create-qr-code/?data="""),format.raw/*37.110*/("""{"""),format.raw/*37.111*/("""{"""),format.raw/*37.112*/("""app.downurl"""),format.raw/*37.123*/("""}"""),format.raw/*37.124*/("""}"""),format.raw/*37.125*/("""&size=120x120"/></p>
                            </div><!--contentinner-->
                        </div><!--content-->
                    </li>

                </ul>
                
            
            </div><!--prodcontent-->
            
            <br clear="all" /><br /><br />
            
         </div><!--prodwrapper-->
            
        </div><!--contentwrapper-->
                
	</div><!-- centercontent -->
	<script type="text/javascript" src=""""),_display_(Seq[Any](/*54.39*/routes/*54.45*/.Assets.at("js/hotappcontroller.js"))),format.raw/*54.81*/(""""></script>
""")))})),format.raw/*55.2*/("""    
"""))}
    }
    
    def render(user:UserModel): play.api.templates.HtmlFormat.Appendable = apply(user)
    
    def f:((UserModel) => play.api.templates.HtmlFormat.Appendable) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Nov 25 17:08:38 CST 2013
                    SOURCE: D:/work/play-2.2.1/appmarket/app/views/hotapps.scala.html
                    HASH: 7125d0ea531515e78d57d42c8ed4c56575bcfcb6
                    MATRIX: 779->1|890->18|929->23|959->45|998->47|1572->593|1601->594|1657->622|1686->623|1787->696|1816->697|1879->732|1908->733|2021->818|2050->819|2079->820|2117->830|2146->831|2175->832|2206->835|2235->836|2264->837|2301->845|2331->846|2361->847|2394->851|2424->852|2454->853|2493->863|2523->864|2553->865|2928->1212|2957->1213|2986->1214|3025->1225|3054->1226|3083->1227|3126->1242|3155->1243|3184->1244|3223->1255|3253->1256|3283->1257|3388->1334|3417->1335|3446->1336|3485->1347|3514->1348|3543->1349|3714->1492|3743->1493|3772->1494|3811->1505|3840->1506|3869->1507|3962->1572|3991->1573|4020->1574|4056->1582|4085->1583|4114->1584|4252->1694|4281->1695|4310->1696|4348->1706|4377->1707|4406->1708|4556->1829|4586->1830|4616->1831|4656->1842|4686->1843|4716->1844|5242->2334|5257->2340|5315->2376|5360->2390
                    LINES: 26->1|29->1|31->3|31->3|31->3|46->18|46->18|46->18|46->18|47->19|47->19|47->19|47->19|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|48->20|57->29|57->29|57->29|57->29|57->29|57->29|57->29|57->29|57->29|57->29|57->29|57->29|58->30|58->30|58->30|58->30|58->30|58->30|61->33|61->33|61->33|61->33|61->33|61->33|62->34|62->34|62->34|62->34|62->34|62->34|64->36|64->36|64->36|64->36|64->36|64->36|65->37|65->37|65->37|65->37|65->37|65->37|82->54|82->54|82->54|83->55
                    -- GENERATED --
                */
            