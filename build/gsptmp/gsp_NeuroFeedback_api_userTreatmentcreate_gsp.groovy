import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_userTreatmentcreate_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/userTreatment/create.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(1)
invokeTag('set','g',5,['var':("entityName"),'value':(message(code: 'userTreatment.label', default: 'UserTreatment'))],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('message','g',6,['code':("default.create.label"),'args':([entityName])],-1)
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
invokeTag('message','g',12,['code':("default.home.label")],-1)
printHtmlPart(5)
createClosureForHtmlPart(6, 2)
invokeTag('link','g',13,['class':("list"),'action':("index")],2)
printHtmlPart(7)
createClosureForHtmlPart(8, 2)
invokeTag('link','g',14,['class':("create"),'action':("create")],2)
printHtmlPart(9)
if(true && (flash.message)) {
printHtmlPart(10)
expressionOut.print(flash.message)
printHtmlPart(11)
}
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(13)
createTagBody(3, {->
printHtmlPart(14)
if(true && (error in org.springframework.validation.FieldError)) {
printHtmlPart(15)
expressionOut.print(error.field)
printHtmlPart(16)
}
printHtmlPart(17)
invokeTag('message','g',28,['error':(error)],-1)
printHtmlPart(18)
})
invokeTag('eachError','g',29,['bean':(this.userTreatment),'var':("error")],3)
printHtmlPart(19)
})
invokeTag('hasErrors','g',31,['bean':(this.userTreatment)],2)
printHtmlPart(12)
createTagBody(2, {->
printHtmlPart(20)
invokeTag('select','g',35,['name':("user"),'from':(patientUsers),'optionKey':("id"),'templates':("bootstrap3"),'optionValue':({it.firstName+' '+it.lastName})],-1)
printHtmlPart(21)
invokeTag('select','g',37,['name':("treatment"),'from':(treatments),'optionKey':("id"),'optionValue':("name"),'templates':("bootstrap3")],-1)
printHtmlPart(22)
invokeTag('field','f',38,['bean':("userTreatment"),'property':("duration"),'templates':("bootstrap3")],-1)
printHtmlPart(22)
invokeTag('field','f',39,['bean':("userTreatment"),'property':("frequency"),'templates':("bootstrap3")],-1)
printHtmlPart(23)
invokeTag('submitButton','g',43,['name':("create"),'class':("save"),'value':(message(code: 'default.button.create.label', default: 'Create'))],-1)
printHtmlPart(24)
})
invokeTag('form','g',46,['resource':(this.userTreatment),'method':("POST")],2)
printHtmlPart(25)
})
invokeTag('captureBody','sitemesh',49,[:],1)
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
