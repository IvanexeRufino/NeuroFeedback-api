import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_apiindex_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/index.gsp" }
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
printHtmlPart(1)
createTagBody(2, {->
printHtmlPart(3)
expressionOut.print(grails.util.Environment.current.name)
printHtmlPart(4)
expressionOut.print(grailsApplication.config.grails?.profile)
printHtmlPart(5)
invokeTag('meta','g',14,['name':("info.app.version")],-1)
printHtmlPart(6)
invokeTag('meta','g',18,['name':("info.app.grailsVersion")],-1)
printHtmlPart(7)
expressionOut.print(GroovySystem.getVersion())
printHtmlPart(8)
expressionOut.print(System.getProperty('java.version'))
printHtmlPart(9)
expressionOut.print(grails.util.Environment.reloadingAgentEnabled)
printHtmlPart(10)
expressionOut.print(grailsApplication.controllerClasses.size())
printHtmlPart(11)
expressionOut.print(grailsApplication.domainClasses.size())
printHtmlPart(12)
expressionOut.print(grailsApplication.serviceClasses.size())
printHtmlPart(13)
expressionOut.print(grailsApplication.tagLibClasses.size())
printHtmlPart(14)
for( plugin in (applicationContext.getBean('pluginManager').allPlugins) ) {
printHtmlPart(15)
expressionOut.print(plugin.name)
printHtmlPart(16)
expressionOut.print(plugin.version)
printHtmlPart(17)
}
printHtmlPart(18)
})
invokeTag('captureContent','sitemesh',43,['tag':("nav")],2)
printHtmlPart(19)
})
invokeTag('captureBody','sitemesh',136,[:],1)
printHtmlPart(20)
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
