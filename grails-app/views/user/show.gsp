<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'user')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><i class="fa fa-list"></i>Lista de Usuarios</g:link></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i>Crear Usuario</g:link></li>
                <li><g:link class="edit" action="edit" resource="${this.user}"><i class="fa fa-edit"></i>Editar Usuario</g:link></li>
                <li><a href="/userTreatment/user/${user.id}"><i class="fa fa-eye"></i>Tratamientos de Usuario</a></li>
            </ul>
        </div>
        <div class="block">
            <div class="block-title">
                <h1>Datos de Usuario</h1>
            </div>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ul>
                <li>Nombre: ${this.user.firstName} ${this.user.lastName}</li>
                <li>Nacimiento: ${this.user.dateOfBirth}</li>
                <li>${this.user.docType} ${this.user.docNumber}</li>
                <li>Email: ${this.user.email}</li>
                <li>Usuario: ${this.user.username}</li>
                <g:if test="${this.user.assignedDoctor}"> 
                    <li>Doctor Asignado: ${this.user.assignedDoctor.firstName} ${this.user.assignedDoctor.lastName}</li>   
                </g:if>
                <g:else>
                    <li>Sin doctor asignado</li>
                </g:else>
                <li>Role: ${this.user.role.beauty}</li>
            </ul>
            <g:form resource="${this.user}" method="DELETE">
                <fieldset class="buttons">
                    <input class="delete btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
            <br>
        </div>
    </body>
</html>
