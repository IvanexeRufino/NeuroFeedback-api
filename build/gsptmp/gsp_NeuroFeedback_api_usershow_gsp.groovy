import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_usershow_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/user/show.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("entityName"),'value':(message(code: 'user.label', default: 'user'))],-1)
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
createClosureForHtmlPart(9, 2)
invokeTag('link','g',14,['class':("edit"),'action':("edit"),'resource':(this.user)],2)
printHtmlPart(10)
expressionOut.print(user.id)
printHtmlPart(11)
if(true && (flash.message)) {
printHtmlPart(12)
expressionOut.print(flash.message)
printHtmlPart(13)
}
printHtmlPart(14)
expressionOut.print(this.user.firstName)
printHtmlPart(15)
expressionOut.print(this.user.lastName)
printHtmlPart(16)
expressionOut.print(this.user.dateOfBirth)
printHtmlPart(7)
expressionOut.print(this.user.docType)
printHtmlPart(15)
expressionOut.print(this.user.docNumber)
printHtmlPart(17)
expressionOut.print(this.user.email)
printHtmlPart(18)
expressionOut.print(this.user.username)
printHtmlPart(19)
if(true && (this.user.assignedDoctor)) {
printHtmlPart(20)
expressionOut.print(this.user.assignedDoctor.firstName)
printHtmlPart(15)
expressionOut.print(this.user.assignedDoctor.lastName)
printHtmlPart(21)
}
else {
printHtmlPart(22)
}
printHtmlPart(23)
expressionOut.print(this.user.role)
printHtmlPart(24)
createTagBody(2, {->
printHtmlPart(25)
expressionOut.print(message(code: 'default.button.delete.label', default: 'Delete'))
printHtmlPart(26)
expressionOut.print(message(code: 'default.button.delete.confirm.message', default: 'Are you sure?'))
printHtmlPart(27)
})
invokeTag('form','g',43,['resource':(this.user),'method':("DELETE")],2)
printHtmlPart(28)
})
invokeTag('captureBody','sitemesh',46,[:],1)
printHtmlPart(29)
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
