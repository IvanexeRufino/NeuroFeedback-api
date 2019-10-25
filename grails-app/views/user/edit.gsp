<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'user.label', default: 'user')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><i class="fa fa-list"></i>Lista de Usuarios</g:link></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i>Crear Usuario</g:link></li>
            </ul>
        </div>
        <div class="block">
            <div class="block-title">
                <h1>Editar Usuario</h1> 
            </div>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.user}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.user}" method="PUT">
                <g:hiddenField name="version" value="${this.user?.version}" />
                <fieldset class="form">
                    <f:field bean="user" property="firstName" templates="bootstrap3"/>
                    <f:field bean="user" property="lastName" templates="bootstrap3"/>
                    <f:field bean="user" property="dateOfBirth" templates="bootstrap3"/>
                    <f:field bean="user" property="docType" templates="bootstrap3"/>
                    <f:field bean="user" property="docNumber" templates="bootstrap3"/>
                    <f:field bean="user" property="email" templates="bootstrap3"/>
                    <f:field bean="user" property="username" templates="bootstrap3"/>
                    <f:field bean="user" property="password" templates="bootstrap3"/>
                    <sec:ifAllGranted roles="ROLE_ADMIN">
                        <f:field bean="user" property="role" templates="bootstrap3"/>
                        <f:field bean="user" property="assignedDoctor" templates="bootstrap3"/>
                    </sec:ifAllGranted>
                </fieldset>
                <br>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
                <br>
            </g:form>
        </div>
    </body>
</html>
