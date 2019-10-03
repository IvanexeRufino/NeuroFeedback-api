<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userTreatment.label', default: 'userTreatment')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><i class="fa fa-list"></i>Lista de Tratamientos de Usuario</g:link></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i>Nuevo Tratamiento de Usuario</g:link></li>
                <li><g:link class="edit" action="edit" resource="${this.userTreatment}"><i class="fa fa-edit"></i><g:message code="default.button.edit.label" default="Edit" /></g:link></li>
               <!-- <li><a href="/userTreatment/delete/2"><i class="fa fa-trash"></i>Eliminar Tratamiento</a></li>-->
            </ul>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="block">
                    <div class="block-title">
                        <h1>Usuario</h1>
                    </div>
                    <ul>
                        <li>Paciente: ${userTreatment.user.firstName} ${userTreatment.user.lastName}</li>
                        <li>${userTreatment.user.docType}: ${userTreatment.user.docNumber}</li>
                        <li>Mail: ${userTreatment.user.email}</li>
                    </ul>
                    <a class="btn btn-info" href="/userTreatment/user/${userTreatment.user.id}" style="width: 100%;">Ver Tratamientos del paciente</a>
                    <br><br>
                </div>
            </div>
            <div class="col-md-6">
                <div class="block">
                    <div class="block-title">
                        <h1>Tratamiento de Usuario</h1>
                    </div>
                    <h3>${userTreatment.treatment.name}</h3>
                    <ul>
                        <li>Frecuencia: ${userTreatment.frequency}</li>
                        <li>Efectividad: ${userTreatment.effectiveness}</li>
                        <li>Fecha: ${userTreatment.treatmentDate}</li>
                        <li>Estado: ${userTreatment.status}</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-12">
                <g:form resource="${this.userTreatment}" method="DELETE">
                <fieldset class="buttons">
                    <input class="delete btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Estás seguro?')}');" />
                </fieldset>
            </g:form>
            </div>
        </div>
    </body>
</html>
