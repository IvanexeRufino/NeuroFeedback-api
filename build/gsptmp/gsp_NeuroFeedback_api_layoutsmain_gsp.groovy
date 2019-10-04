import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_layoutsmain_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/main.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',7,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("utf-8")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',8,['default':("Grails")],-1)
})
invokeTag('captureTitle','sitemesh',8,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',8,[:],2)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',9,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("viewport"),'content':("width=device-width,initial-scale=1,maximum-scale=1.0")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',10,['src':("bootstrap.min.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',11,['src':("plugins.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',12,['src':("main.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',13,['src':("style.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',14,['src':("themes.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',15,['src':("theme2.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',16,['src':("custom.css")],-1)
printHtmlPart(2)
invokeTag('stylesheet','asset',17,['src':("datatables.min.css")],-1)
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',19,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(4)
createTagBody(2, {->
printHtmlPart(5)
invokeTag('img','g',62,['dir':("images"),'file':("avatar.jpg")],-1)
printHtmlPart(6)
createClosureForHtmlPart(7, 3)
invokeTag('message','g',68,['message':("Bienvenido ${neurofeedback.currentUserProps(username: true)}")],3)
printHtmlPart(8)
})
invokeTag('ifLoggedIn','sec',70,[:],2)
printHtmlPart(9)
createTagBody(2, {->
printHtmlPart(10)
createClosureForHtmlPart(11, 3)
invokeTag('link','g',76,['controller':("login"),'action':("auth")],3)
printHtmlPart(12)
})
invokeTag('ifNotLoggedIn','sec',77,[:],2)
printHtmlPart(12)
createClosureForHtmlPart(12, 2)
invokeTag('ifLoggedIn','sec',79,[:],2)
printHtmlPart(13)
createTagBody(2, {->
printHtmlPart(10)
for( c in (grailsApplication.controllerClasses.sort { it.fullName }) ) {
printHtmlPart(7)
if(true && (c.getStaticPropertyValue('administrator', Boolean))) {
printHtmlPart(14)
createTagBody(5, {->
printHtmlPart(15)
expressionOut.print(c.getStaticPropertyValue('adminFriendlyName', String))
printHtmlPart(16)
})
invokeTag('link','g',87,['controller':(c.logicalPropertyName)],5)
printHtmlPart(17)
}
printHtmlPart(10)
}
printHtmlPart(12)
})
invokeTag('ifAllGranted','sec',91,['roles':("ROLE_ADMIN")],2)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(10)
for( c in (grailsApplication.controllerClasses.sort { it.fullName }) ) {
printHtmlPart(7)
if(true && (c.getStaticPropertyValue('professional', Boolean))) {
printHtmlPart(14)
createTagBody(5, {->
printHtmlPart(15)
expressionOut.print(c.getStaticPropertyValue('friendlyName', String))
printHtmlPart(16)
})
invokeTag('link','g',98,['controller':(c.logicalPropertyName)],5)
printHtmlPart(17)
}
printHtmlPart(10)
}
printHtmlPart(12)
})
invokeTag('ifAllGranted','sec',102,['roles':("ROLE_PROFESSIONAL")],2)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(12)
for( c in (grailsApplication.controllerClasses.sort { it.fullName }) ) {
printHtmlPart(10)
if(true && (c.getStaticPropertyValue('patient', Boolean))) {
printHtmlPart(18)
createTagBody(5, {->
printHtmlPart(16)
expressionOut.print(c.getStaticPropertyValue('friendlyName', String))
printHtmlPart(19)
})
invokeTag('link','g',109,['controller':(c.logicalPropertyName)],5)
printHtmlPart(20)
}
printHtmlPart(12)
}
printHtmlPart(12)
})
invokeTag('ifAllGranted','sec',113,['roles':("ROLE_PATIENT")],2)
printHtmlPart(12)
createClosureForHtmlPart(21, 2)
invokeTag('ifLoggedIn','sec',118,[:],2)
printHtmlPart(22)
invokeTag('layoutBody','g',173,[:],-1)
printHtmlPart(23)
})
invokeTag('captureBody','sitemesh',207,[:],1)
printHtmlPart(24)
invokeTag('javascript','asset',209,['src':("jquery-2.2.0.min.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',210,['src':("bootstrap.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',211,['src':("plugins.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',212,['src':("app.js")],-1)
printHtmlPart(2)
invokeTag('javascript','asset',1,['src':("dataTables.min.js")],-1)
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
