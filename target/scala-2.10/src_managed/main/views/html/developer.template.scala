
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
object developer extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[UserModel,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(user: UserModel):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main(user, "Upload")/*3.22*/ {_display_(Seq[Any](format.raw/*3.24*/("""

    <div class="centercontent" ng-app>
          <div class="contenttitle2">
                	<h3>Upload Your APP</h3>
                </div><!--contenttitle-->
                						<p>
				                        <span class="btn btn-success fileinput-button">
									        <i class="glyphicon glyphicon-plus"></i>
									        <span>选择文件...</span>
									        <!-- The file input field used as target for the file upload widget -->
									        <input id="file_upload" name="files[]" type="file" multiple="false" data-url='"""),_display_(Seq[Any](/*14.97*/routes/*14.103*/.Developer.uploadAPK())),format.raw/*14.125*/("""'>
									    </span>
									    <br>
									    <!-- The global progress bar -->
									    <div id="progress" class="progress">
									        <div class="progress-bar progress-bar-success"></div>
									    </div>
				                        </p>
                <table cellpadding="0" cellspacing="0" border="0" class="stdtable" id="dyntable2" ng-controller="AppsController">
                    <colgroup>
                        <col class="con0" style="width: 4%" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                        <col class="con0" />
                        <col class="con1" />
                    </colgroup>
                    <thead>
                        <tr>
                          <th class="head0 nosort"><input type="checkbox" /></th>
                            <th class="head0">APP Name</th>
                            <th class="head1">Version</th>
                            <th class="head0">Version Code</th>
                            <th class="head1">Package Name</th>
                            <th class="head0">Icon</th>
                            <th class="head1">Downloads</th>
                            <th class="head0">Upload Time</th>
                            <th class="head1" style="width:10%">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="gradeX" ng-repeat="app in apps">
                          <td align="center"><span class="center">
                            <input type="checkbox" value=""""),format.raw/*47.59*/("""{"""),format.raw/*47.60*/("""{"""),format.raw/*47.61*/("""app.id"""),format.raw/*47.67*/("""}"""),format.raw/*47.68*/("""}"""),format.raw/*47.69*/(""""/>
                          </span></td>
                            <td>"""),format.raw/*49.33*/("""{"""),format.raw/*49.34*/("""{"""),format.raw/*49.35*/("""app.appname"""),format.raw/*49.46*/("""}"""),format.raw/*49.47*/("""}"""),format.raw/*49.48*/("""</td>
                            <td>"""),format.raw/*50.33*/("""{"""),format.raw/*50.34*/("""{"""),format.raw/*50.35*/("""app.appVersion"""),format.raw/*50.49*/("""}"""),format.raw/*50.50*/("""}"""),format.raw/*50.51*/("""</td>
                            <td>"""),format.raw/*51.33*/("""{"""),format.raw/*51.34*/("""{"""),format.raw/*51.35*/("""app.appVersionCode"""),format.raw/*51.53*/("""}"""),format.raw/*51.54*/("""}"""),format.raw/*51.55*/("""</td>
                            <td>"""),format.raw/*52.33*/("""{"""),format.raw/*52.34*/("""{"""),format.raw/*52.35*/("""app.packageName"""),format.raw/*52.50*/("""}"""),format.raw/*52.51*/("""}"""),format.raw/*52.52*/("""</td>
                            <td><img ng-src='"""),_display_(Seq[Any](/*53.47*/routes/*53.53*/.Developer.showImage("{{app.iconUrl}}"))),format.raw/*53.92*/("""' style="width:100px;height:100px"/></td>
                            <td>
                            <a ng-href='"""),_display_(Seq[Any](/*55.42*/routes/*55.48*/.Developer.downloadApk("{{app.downurl}}"))),format.raw/*55.89*/("""'>DOWNLOAD</a>
                            </td>
                            <td>"""),format.raw/*57.33*/("""{"""),format.raw/*57.34*/("""{"""),format.raw/*57.35*/("""app.createdAt"""),format.raw/*57.48*/("""}"""),format.raw/*57.49*/("""}"""),format.raw/*57.50*/("""</td>
                            <td class="center">
                            <ul class="buttonlist">
		                        <li class="deleteCourse"><button  ng-click='deleteApp(""""),format.raw/*60.82*/("""{"""),format.raw/*60.83*/("""{"""),format.raw/*60.84*/("""app.id"""),format.raw/*60.90*/("""}"""),format.raw/*60.91*/("""}"""),format.raw/*60.92*/("""")' class="stdbtn btn_red">Delete</button></li>
		                   </ul>
                            </td>
                        </tr>
                    """)))})),format.raw/*64.22*/("""
                    </tbody>
                </table>
	</div><!-- centercontent -->    
	<script type="text/javascript" src=""""),_display_(Seq[Any](/*68.39*/routes/*68.45*/.Assets.at("js/developercontroller.js"))),format.raw/*68.84*/(""""></script>
	<link rel="stylesheet" href="""),_display_(Seq[Any](/*69.31*/routes/*69.37*/.Assets.at("css/jquery.fileupload.css"))),format.raw/*69.76*/(""">
<link rel="stylesheet" href="""),_display_(Seq[Any](/*70.30*/routes/*70.36*/.Assets.at("css/jquery.fileupload-ui.css"))),format.raw/*70.78*/(""">
<script type="text/javascript" src="""),_display_(Seq[Any](/*71.37*/routes/*71.43*/.Assets.at("js/jquery.fileupload.js"))),format.raw/*71.80*/("""></script>
	<script>
//load upload image file
	jQuery('#file_upload').fileupload("""),format.raw/*74.36*/("""{"""),format.raw/*74.37*/("""
        url: '"""),_display_(Seq[Any](/*75.16*/routes/*75.22*/.Developer.uploadAPK())),format.raw/*75.44*/("""',
        dataType: 'json',
        done: function (e, data) """),format.raw/*77.34*/("""{"""),format.raw/*77.35*/("""
        	alert(e);
        	alert(data);
        	if(data.index("error:") > 0)"""),format.raw/*80.38*/("""{"""),format.raw/*80.39*/("""
        	alert(data);
        	"""),format.raw/*82.10*/("""}"""),format.raw/*82.11*/("""else"""),format.raw/*82.15*/("""{"""),format.raw/*82.16*/("""
			alert("Successfully upload apk!");
			"""),format.raw/*84.4*/("""}"""),format.raw/*84.5*/("""
        """),format.raw/*85.9*/("""}"""),format.raw/*85.10*/("""
    """),format.raw/*86.5*/("""}"""),format.raw/*86.6*/(""").prop('disabled', !jQuery.support.fileInput)
        .parent().addClass(jQuery.support.fileInput ? undefined : 'disabled');
        </script>
"""))}
    }
    
    def render(user:UserModel): play.api.templates.HtmlFormat.Appendable = apply(user)
    
    def f:((UserModel) => play.api.templates.HtmlFormat.Appendable) = (user) => apply(user)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Mon Nov 25 17:08:38 CST 2013
                    SOURCE: D:/work/play-2.2.1/appmarket/app/views/developer.scala.html
                    HASH: a27e3cba4e5d7cb32b9a9ed03f27be039479eafb
                    MATRIX: 781->1|892->18|931->23|959->43|998->45|1591->602|1607->608|1652->630|3378->2328|3407->2329|3436->2330|3470->2336|3499->2337|3528->2338|3633->2415|3662->2416|3691->2417|3730->2428|3759->2429|3788->2430|3855->2469|3884->2470|3913->2471|3955->2485|3984->2486|4013->2487|4080->2526|4109->2527|4138->2528|4184->2546|4213->2547|4242->2548|4309->2587|4338->2588|4367->2589|4410->2604|4439->2605|4468->2606|4557->2659|4572->2665|4633->2704|4787->2822|4802->2828|4865->2869|4976->2952|5005->2953|5034->2954|5075->2967|5104->2968|5133->2969|5351->3159|5380->3160|5409->3161|5443->3167|5472->3168|5501->3169|5697->3333|5864->3464|5879->3470|5940->3509|6019->3552|6034->3558|6095->3597|6163->3629|6178->3635|6242->3677|6317->3716|6332->3722|6391->3759|6503->3843|6532->3844|6585->3861|6600->3867|6644->3889|6736->3953|6765->3954|6875->4036|6904->4037|6966->4071|6995->4072|7027->4076|7056->4077|7127->4121|7155->4122|7192->4132|7221->4133|7254->4139|7282->4140
                    LINES: 26->1|29->1|31->3|31->3|31->3|42->14|42->14|42->14|75->47|75->47|75->47|75->47|75->47|75->47|77->49|77->49|77->49|77->49|77->49|77->49|78->50|78->50|78->50|78->50|78->50|78->50|79->51|79->51|79->51|79->51|79->51|79->51|80->52|80->52|80->52|80->52|80->52|80->52|81->53|81->53|81->53|83->55|83->55|83->55|85->57|85->57|85->57|85->57|85->57|85->57|88->60|88->60|88->60|88->60|88->60|88->60|92->64|96->68|96->68|96->68|97->69|97->69|97->69|98->70|98->70|98->70|99->71|99->71|99->71|102->74|102->74|103->75|103->75|103->75|105->77|105->77|108->80|108->80|110->82|110->82|110->82|110->82|112->84|112->84|113->85|113->85|114->86|114->86
                    -- GENERATED --
                */
            