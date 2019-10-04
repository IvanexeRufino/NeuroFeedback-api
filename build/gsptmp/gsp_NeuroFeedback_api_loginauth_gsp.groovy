import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_loginauth_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/login/auth.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('stylesheet','asset',1,['src':("bootstrap.min.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',2,['src':("plugins.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',3,['src':("main.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',4,['src':("style.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',5,['src':("themes.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',6,['src':("theme2.css")],-1)
printHtmlPart(1)
invokeTag('stylesheet','asset',7,['src':("custom.css")],-1)
printHtmlPart(2)
invokeTag('img','g',9,['dir':("images"),'file':("background.jpg"),'class':("full-bg animation-pulseSlow")],-1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
invokeTag('message','g',25,['code':("spring.security.ui.login.username")],-1)
printHtmlPart(5)
expressionOut.print(securityConfig.apf.usernameParameter)
printHtmlPart(6)
invokeTag('message','g',33,['code':("spring.security.ui.login.password")],-1)
printHtmlPart(7)
expressionOut.print(securityConfig.apf.passwordParameter)
printHtmlPart(8)
expressionOut.print(securityConfig.rememberMe.parameter)
printHtmlPart(9)
})
invokeTag('form','s2ui',54,['type':("login"),'class':("form-horizontal form-bordered form-control-borderless"),'focus':("username")],1)
printHtmlPart(10)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1570138023000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'none'
public static final String TAGLIB_CODEC = 'none'
}
