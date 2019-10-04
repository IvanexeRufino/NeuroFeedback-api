import grails.plugins.metadata.GrailsPlugin
import org.grails.gsp.compiler.transform.LineNumber
import org.grails.gsp.GroovyPage
import org.grails.web.taglib.*
import org.grails.taglib.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_NeuroFeedback_api_templates__fields_table_gsp extends org.grails.gsp.GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/templates/_fields/_table.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
loop:{
int i = 0
for( p in (domainProperties) ) {
printHtmlPart(1)
invokeTag('sortableColumn','g',5,['property':(p.property),'title':(p.label)],-1)
printHtmlPart(2)
i++
}
}
printHtmlPart(3)
loop:{
int i = 0
for( bean in (collection) ) {
printHtmlPart(4)
expressionOut.print((i % 2) == 0 ? 'even' : 'odd')
printHtmlPart(5)
loop:{
int j = 0
for( p in (domainProperties) ) {
printHtmlPart(6)
if(true && (j==0)) {
printHtmlPart(7)
createTagBody(4, {->
invokeTag('display','f',14,['bean':(bean),'property':(p.property),'displayStyle':(displayStyle?:'table'),'theme':(theme)],-1)
})
invokeTag('link','g',14,['method':("GET"),'resource':(bean)],4)
printHtmlPart(8)
}
else {
printHtmlPart(7)
invokeTag('display','f',17,['bean':(bean),'property':(p.property),'displayStyle':(displayStyle?:'table'),'theme':(theme)],-1)
printHtmlPart(8)
}
printHtmlPart(1)
j++
}
}
printHtmlPart(9)
i++
}
}
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
