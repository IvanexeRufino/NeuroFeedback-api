import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_userTreatmentshow_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userTreatment/show.gsp" }
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
invokeTag('message','g',6,['code':("default.show.label"),'args':([entityName])],-1)
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
createClosureForHtmlPart(6, 2)
invokeTag('link','g',12,['class':("list"),'action':("index")],2)
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',13,['class':("create"),'action':("create")],2)
printHtmlPart(7)
createTagBody(2, {->
printHtmlPart(9)
invokeTag('message','g',14,['code':("default.button.edit.label"),'default':("Edit")],-1)
})
invokeTag('link','g',14,['class':("edit"),'action':("edit"),'resource':(this.userTreatment)],2)
printHtmlPart(10)
expressionOut.print(userTreatment.user.firstName)
printHtmlPart(11)
expressionOut.print(userTreatment.user.lastName)
printHtmlPart(12)
expressionOut.print(userTreatment.user.docType)
printHtmlPart(13)
expressionOut.print(userTreatment.user.docNumber)
printHtmlPart(14)
expressionOut.print(userTreatment.user.email)
printHtmlPart(15)
expressionOut.print(userTreatment.user.id)
printHtmlPart(16)
expressionOut.print(userTreatment.treatment.name)
printHtmlPart(17)
expressionOut.print(userTreatment.frequency)
printHtmlPart(18)
expressionOut.print(userTreatment.effectiveness)
printHtmlPart(19)
expressionOut.print(userTreatment.treatmentDate)
printHtmlPart(20)
expressionOut.print(userTreatment.status)
printHtmlPart(21)
createTagBody(2, {->
printHtmlPart(22)
createTagBody(3, {->
printHtmlPart(23)
expressionOut.print(message(code: 'default.button.delete.label', default: 'Delete'))
printHtmlPart(24)
expressionOut.print(message(code: 'default.button.delete.confirm.message', default: 'Estás seguro?'))
printHtmlPart(25)
})
invokeTag('form','g',53,['resource':(this.userTreatment),'method':("DELETE")],3)
printHtmlPart(26)
})
invokeTag('ifAllGranted','sec',54,['roles':("ROLE_PROFESSIONAL")],2)
printHtmlPart(27)
})
invokeTag('captureBody','sitemesh',58,[:],1)
printHtmlPart(28)
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
