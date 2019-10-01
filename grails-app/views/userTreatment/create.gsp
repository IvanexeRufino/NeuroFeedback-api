<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userTreatment.label', default: 'UserTreatment')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        
        <div class="content-header" role="navigation">
            <ul class="nav-horizontal text-center">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><i class="fa fa-list"></i><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><i class="fa fa-plus"></i><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div class="block">
            <div id="create-userTreatment" class="content scaffold-create" role="main">
                <div class="block-title">
                    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
            <g:form resource="${this.userTreatment}" method="POST">
                <fieldset class="form">
                    <g:select name="user"
                              from="${patientUsers}"
                              optionKey="id"/>
                    <f:field bean="userTreatment" property="treatment" templates="bootstrap3"/>
                    <f:field bean="userTreatment" property="duration" templates="bootstrap3"/>
                    <f:field bean="userTreatment" property="frequency" templates="bootstrap3"/>
                </fieldset>
                <br>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
                <br>
            </g:form>
        </div>
        </div>
    </body>
</html>
