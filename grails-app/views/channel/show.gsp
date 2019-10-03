<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'channel.label', default: 'Channel')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><i class="fa fa-list"></i><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div class="block">
           <div id="show-channel" class="content scaffold-show" role="main">
            <div class="block-title">   
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            </div>
                <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
                </g:if>
                <f:display bean="channel" />
                <g:form resource="${this.channel}" method="DELETE">
                    <fieldset class="buttons">
                        <g:link class="edit btn btn-info" style="width: 49%;" action="edit" resource="${this.channel}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                        <input class="delete btn" style="width: 49%;"  type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                    </fieldset>
                </g:form>
            </div>
        </div>
        
    </body>
</html>
