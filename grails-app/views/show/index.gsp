<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'channel.label', default: 'Channel')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="row">
            <g:each in="${treatments}">
                <div class="col-md-6">
                    <div class="block" style="align-content: center;text-align: center;">
                        <div class="block-title">
                            <h4>${it.name}</h4>
                        </div>
                        <p style="align-self: center;">${it.description} </p>
                        <g:img dir="images"  width="60%" height="60%" file="treatments/${it.id}.png"/>
                        <br><br><br>
                        <div class="block-title">
                            <h4>
                                Canales utilizados:     
                                <g:each in="${it.getChannels()}" var="canales">
                                    ${canales}
                                </g:each>
                            </h4>
                        </div>
                    </div>
                </div>
            </g:each>
        </div>        
    </body>
</html>
