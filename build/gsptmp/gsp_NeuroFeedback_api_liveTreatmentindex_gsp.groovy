import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_liveTreatmentindex_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/liveTreatment/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',5,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(4)
invokeTag('message','g',9,['code':("default.home.label")],-1)
printHtmlPart(5)
invokeTag('message','g',15,['code':("Tratamientos siendo realizados")],-1)
printHtmlPart(6)
for( userTreatment in (userTreatmentLive) ) {
printHtmlPart(7)
expressionOut.print(userTreatment.user.username)
printHtmlPart(8)
expressionOut.print(userTreatment.treatment.name)
printHtmlPart(8)
createTagBody(3, {->
invokeTag('message','g',34,['code':("WATCH LIVE")],-1)
})
invokeTag('link','g',34,['class':("save"),'action':("live"),'id':(userTreatment.id)],3)
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (flash.message)) {
printHtmlPart(11)
expressionOut.print(flash.message)
printHtmlPart(12)
}
printHtmlPart(13)
invokeTag('paginate','g',44,['total':(userTreatmentLiveCount ?: 0)],-1)
printHtmlPart(14)
})
invokeTag('captureBody','sitemesh',48,[:],1)
printHtmlPart(15)
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
