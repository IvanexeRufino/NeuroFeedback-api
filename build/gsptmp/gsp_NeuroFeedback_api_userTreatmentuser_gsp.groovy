import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_userTreatmentuser_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userTreatment/user.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("entityName"),'value':(message(code: 'userTreatment.label', default: 'userTreatment'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("default.list.label"),'args':([entityName])],-1)
})
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(2)
})
invokeTag('captureHead','sitemesh',7,[:],1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(4)
invokeTag('message','g',11,['code':("default.home.label")],-1)
printHtmlPart(5)
for( userTreatment in (userTreatmentPending) ) {
printHtmlPart(6)
expressionOut.print(userTreatment.id)
printHtmlPart(7)
expressionOut.print(userTreatment.id)
printHtmlPart(8)
expressionOut.print(userTreatment.frequency)
printHtmlPart(9)
expressionOut.print(userTreatment.treatmentDate)
printHtmlPart(9)
expressionOut.print(userTreatment.duration)
printHtmlPart(9)
expressionOut.print(userTreatment.effectiveness)
printHtmlPart(9)
expressionOut.print(userTreatment.status)
printHtmlPart(10)
expressionOut.print(userTreatment.user.firstName)
printHtmlPart(11)
expressionOut.print(userTreatment.treatment.name)
printHtmlPart(12)
}
printHtmlPart(13)
if(true && (flash.message)) {
printHtmlPart(14)
expressionOut.print(flash.message)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('paginate','g',63,['total':(userTreatmentPendingCount ?: 0)],-1)
printHtmlPart(17)
invokeTag('message','g',71,['code':("Historial de tratamientos"),'args':([entityName])],-1)
printHtmlPart(18)
for( userTreatment in (userTreatmentFinished) ) {
printHtmlPart(6)
expressionOut.print(userTreatment.id)
printHtmlPart(7)
expressionOut.print(userTreatment.id)
printHtmlPart(8)
expressionOut.print(userTreatment.frequency)
printHtmlPart(9)
expressionOut.print(userTreatment.treatmentDate)
printHtmlPart(9)
expressionOut.print(userTreatment.duration)
printHtmlPart(9)
expressionOut.print(userTreatment.effectiveness)
printHtmlPart(9)
expressionOut.print(userTreatment.status)
printHtmlPart(10)
expressionOut.print(userTreatment.user.firstName)
printHtmlPart(11)
expressionOut.print(userTreatment.treatment.name)
printHtmlPart(12)
}
printHtmlPart(19)
if(true && (flash.message)) {
printHtmlPart(14)
expressionOut.print(flash.message)
printHtmlPart(15)
}
printHtmlPart(16)
invokeTag('paginate','g',118,['total':(userTreatmentFinishedCount ?: 0)],-1)
printHtmlPart(20)
})
invokeTag('captureBody','sitemesh',122,[:],1)
printHtmlPart(21)
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
