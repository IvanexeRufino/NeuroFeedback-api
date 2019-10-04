import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_userTreatmenthistory_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userTreatment/history.gsp" }
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
invokeTag('captureHead','sitemesh',8,[:],1)
printHtmlPart(3)
createTagBody(1, {->
printHtmlPart(4)
expressionOut.print(createLink(uri: '/'))
printHtmlPart(5)
invokeTag('message','g',18,['code':("default.home.label")],-1)
printHtmlPart(6)
expressionOut.print(tratamiento.duration)
printHtmlPart(7)
expressionOut.print(tratamiento.status)
printHtmlPart(8)
expressionOut.print(tratamiento.treatmentDate)
printHtmlPart(9)
expressionOut.print(user.firstName)
printHtmlPart(10)
expressionOut.print(user.lastName)
printHtmlPart(11)
for( htratamientos in (tratamientos) ) {
printHtmlPart(12)
if(true && (htratamientos.id.equals(tratamiento.id))) {
printHtmlPart(13)
}
else {
printHtmlPart(14)
}
printHtmlPart(12)
if(true && (htratamientos.effectiveness < 50)) {
printHtmlPart(15)
}
else {
printHtmlPart(16)
}
printHtmlPart(17)
expressionOut.print(htratamientos.treatmentDate)
printHtmlPart(18)
expressionOut.print(htratamientos.status)
printHtmlPart(19)
expressionOut.print(htratamientos.duration)
printHtmlPart(20)
expressionOut.print(htratamientos.effectiveness)
printHtmlPart(21)
expressionOut.print(htratamientos.id)
printHtmlPart(22)
}
printHtmlPart(23)
expressionOut.print(tratamiento.effectiveness)
printHtmlPart(24)
expressionOut.print(100 - tratamiento.effectiveness)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',127,[:],1)
printHtmlPart(26)
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
