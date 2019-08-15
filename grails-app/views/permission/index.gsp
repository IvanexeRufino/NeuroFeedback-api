<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'permission.label', default: 'Permission')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a href="#list-permission" class="skip" tabindex="-1"><i class="fa fa-arrow-left"></i><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a></li>
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-permission" class="col-md-12" role="main">
            <div class="block">
                <div class="block-title">
                    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
                </div>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <f:table collection="${permissionList}" />
                <div class="pagination">
                    <g:paginate total="${permissionCount ?: 0}" />
                </div>
            </div>
            
        </div>
    </body>
</html>