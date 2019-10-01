<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'userTreatment.label', default: 'userTreatment')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
    <div class="content-header" role="navigation">
        <ul class="nav-horizontal text-center">
            <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
            <li><g:link class="list" action="index"><i class="fa fa-list"></i><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            <li><g:link class="create" action="create"><i class="fa fa-plus"></i><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </ul>
    </div>

    <div id="list-pending-userTreatment" class="col-md-12" role="main">
        <div class="block">
            <div class="block-title">
                <h1><g:message code="Tratamientos pendientes" args="[entityName]" /></h1>
            </div>
            <div></div>
            <div class="table table-responsive" style="text-align: center;">
                <f:table collection="${userTreatmentPending}"/>
            </div>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div class="pagination">
                <g:paginate total="${userTreatmentPendingCount ?: 0}" />
            </div>
        </div>
    </div>
    
    <div id="list-finished-userTreatment" class="col-md-12" role="main">
        <div class="block">
            <div class="block-title">
                <h1><g:message code="Historial de tratamientos" args="[entityName]" /></h1>
            </div>
            <div></div>
            <div class="table table-responsive" style="text-align: center;">
                <f:table collection="${userTreatmentFinished}"/>
            </div>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div class="pagination">
                <g:paginate total="${userTreatmentFinishedCount ?: 0}" />
            </div>
        </div>
    </div>
</body>
</html>