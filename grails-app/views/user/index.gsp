<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'user')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><i class="fa fa-list"></i>Lista de Usuarios</g:link></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i>Crear Usuario</g:link></li>
            </ul>
        </div>
        <div id="list-user" class="col-md-12" role="main">
            <div class="block">
                <div class="block-title">
                    <h1>Listado de Usuarios</h1>
                </div> 

                <g:if test="${userList.size() != 0}">
                    <div class="table table-responsive" style="text-align: center;">
                        <table class="table table-striped" id="table-pending">
                        <tr>
                            <th class="sortable">N°</th>
                            <th class="sortable"><a href="/user/index?sort=firstName&amp;max=10&amp;order=asc">Nombre</a></th>
                            <th class="sortable"><a href="/user/index?sort=lastName&amp;max=10&amp;order=asc">Apellido</a></th>
                            <th class="sortable"><a href="/user/index?sort=dateOfBirth&amp;max=10&amp;order=desc">Nacimiento</a></th>
                            <th class="sortable"><a href="/user/index?sort=docType&amp;max=10&amp;order=asc">Tipo Documento</a></th>
                            <th class="sortable"><a href="/user/index?sort=docNumber&amp;max=10&amp;order=asc">N° Documento</a></th>
                            <th class="sortable"><a href="/user/index?sort=email&amp;max=10&amp;order=asc">Email</a></th>
                        </tr>
                        <g:each var="user" in="${userList}">
                            <tr>
                                <td>
                                    <a href="/user/show/${user.id}">${user.id}</a>
                                </td>
                                <td>
                                    ${user.firstName}
                                </td>
                                <td>
                                    ${user.lastName}
                                </td>
                                <td>
                                    ${user.dateOfBirth}
                                </td>
                                <td>
                                    ${user.docType}
                                </td>
                                <td>
                                    ${user.docNumber}
                                </td>
                                <td>${user.email}</td>
                            </tr>
                        </g:each>
                    </table>
                    </div>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <div class="pagination">
                        <g:paginate total="${userCount ?: 0}" />
                    </div>
                </g:if>
                
                <g:else>
                    <h4>No tiene personas a cargo</h4>
                </g:else>
            </div>
            
        </div>
    </body>
</html>