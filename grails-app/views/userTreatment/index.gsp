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
        </ul>
    </div>
    <div class="row">      
        <div id="list-pending-userTreatment" class="col-md-12" role="main">
            <div class="block">
                <div class="block-title">
                    <h1>Tratamientos pendientes</h1>
                </div>
                <g:if test="${userTreatmentPending.size() != 0}">

                    <div class="table-responsive" style="text-align: center;">
                        <table class="table table-striped" id="table-pending">
                            <tr>
                                <th class="sortable"><a href="/userTreatment/index?sort=id&amp;max=10&amp;order=asc">N°</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=frequency&amp;max=10&amp;order=asc">Frecuencia</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=treatmentDate&amp;max=10&amp;order=asc">Duración</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=user&amp;max=10&amp;order=asc">Usuario</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=treatment&amp;max=10&amp;order=asc">Tratamiento</a></th>
                            </tr>
                            <g:each var="userTreatment" in="${userTreatmentPending}">
                                <tr>
                                    <td>
                                        <a href="/userTreatment/show/${userTreatment.id}">${userTreatment.id}</a>
                                    </td>
                                    <td>
                                        ${userTreatment.frequency}
                                    </td>
                                    <td>
                                        ${userTreatment.duration}"
                                    </td>
                                    <td>${userTreatment.user.firstName}</td>
                                    <td>${userTreatment.treatment.name}</td>
                                </tr>
                            </g:each>
                        </table>
                    </div>
                    <div class="pagination">
                        <g:paginate total="${userTreatmentPendingCount ?: 0}" />
                    </div>
                </g:if> 
                <g:else>
                    <h4>No existen tratamientos pendientes</h4>
                </g:else>
            </div>
        </div>
        
        <div id="list-finished-userTreatment" class="col-md-12" role="main">
            <div class="block">
                <div class="block-title">
                    <h1><g:message code="Historial de tratamientos" args="[entityName]" /></h1>
                </div>
                <g:if test="${userTreatmentFinished.size() != 0}">
                    <div class="table table-responsive" style="text-align: center;">
                        <div class="table-responsive" style="text-align: center;">
                        <table class="table table-striped" id="table-pending">
                            <tr>
                                <th class="sortable"><a href="/userTreatment/index?sort=id&amp;max=10&amp;order=asc">N°</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=frequency&amp;max=10&amp;order=asc">Frecuencia</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=treatmentDate&amp;max=10&amp;order=asc">Fecha Tratamiento</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=duration&amp;max=10&amp;order=asc">Duración</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=effectiveness&amp;max=10&amp;order=asc">Efectividad</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=user&amp;max=10&amp;order=asc">Usuario</a></th>
                                <th class="sortable"><a href="/userTreatment/index?sort=treatment&amp;max=10&amp;order=asc">Tratamiento</a></th>
                                <th></th>
                            </tr>
                            <g:each var="userTreatment" in="${userTreatmentFinished}">
                                <tr>
                                    <td>
                                        <a href="/userTreatment/show/${userTreatment.id}">${userTreatment.id}</a>
                                    </td>
                                    <td>
                                        ${userTreatment.frequency}
                                    </td>
                                    <td>
                                        ${userTreatment.treatmentDate}
                                    </td>
                                    <td>
                                        ${userTreatment.duration}"
                                    </td>
                                    <td>
                                        ${userTreatment.effectiveness}
                                    </td>
                                    <td>${userTreatment.user.firstName}</td>
                                    <td>${userTreatment.treatment.name}</td>
                                    <td><a href="/userTreatment/history/${userTreatment.id}"><i class="fa fa-eye"></i></a></td>
                                </tr>
                            </g:each>
                        </table>
                        </div>
                    </div>
                    <div class="pagination">
                        <g:paginate total="${userTreatmentFinishedCount ?: 0}" />
                    </div>
                </g:if>
                <g:else>
                    <h4>No existen tratamientos realizados</h4>
                </g:else>
            </div>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
        </div>

    </div>
</body>
</html>
