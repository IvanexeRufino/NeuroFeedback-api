<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userHistory.label', default: 'userHistory')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-userHistory" class="col-md-6" role="main">
            <div class="block">
                <div class="block-title">
                    <h1><g:message code="Historial de tratamientos" args="[entityName]" /></h1>
                </div>
                <div></div>
                <div class="table table-responsive" style="text-align: center;">
                    <f:table collection="${userHistoryList}"/>    
                </div>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
                <div class="pagination">
                    <g:paginate total="${userHistoryCount ?: 0}" />
                </div>
            </div>
            
        </div>
    </body>
</html>