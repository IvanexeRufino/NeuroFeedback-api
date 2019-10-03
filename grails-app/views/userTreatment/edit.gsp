<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userTreatment.label', default: 'userTreatment')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><i class="fa fa-list"></i>Lista de Tratamientos de Usuario</g:link></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i>Nuevo Tratamiento de Usuario</g:link></li>
            </ul>
        </div>
        <div class="block">
            <div class="block-title">
                <h1>Editar Usuario</h1> 
            </div>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.userTreatment}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.userTreatment}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>

            <sec:ifAllGranted roles="ROLE_PROFESSIONAL">
                 <g:form resource="${this.userTreatment}" method="PUT">
                    <g:hiddenField name="version" value="${this.userTreatment?.version}" />
                    <fieldset class="form">
                        <f:all bean="userTreatment"/>
                    </fieldset>
                    <br>
                    <fieldset class="buttons">
                        <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                    </fieldset>
                    <br>
                </g:form>
            </sec:ifAllGranted>
        </div>
    </body>
</html>
