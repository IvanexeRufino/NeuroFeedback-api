<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>
<div class="content-header" role="navigation">
    <ul class="nav-horizontal text-center">
        <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
    </ul>
</div>
<div id="list-finished-userTreatment" class="col-md-12" role="main">
    <div class="block">
        <div class="block-title">
            <h1><g:message code="Tratamientos siendo realizados"/></h1>
        </div>
        <div></div>
        <div class="table table-responsive" style="text-align: center;">
        <table width='100%'>
            <tr>
                <th>Usuario</th>
                <th>Tratamiento</th>
                <th>Ver Tratamiento</th>
            </tr>
            <g:each var="userTreatment" in="${userTreatmentLive}">
                <tr>
                    <td>
                        ${userTreatment.user.username}
                    </td>
                    <td>
                        ${userTreatment.treatment.name}
                    </td>
                    <td>
                        <g:link class="save" action="live" id="${userTreatment.id}"><g:message code="EN VIVO" /></g:link>
                    </td>
                </tr>
            </g:each>
        </table>
        </div>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <div class="pagination">
            <g:paginate total="${userTreatmentLiveCount ?: 0}" />
        </div>
    </div>
</div>
</body>
</html>