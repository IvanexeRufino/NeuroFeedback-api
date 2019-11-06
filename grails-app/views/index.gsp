<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <div class="content-header content-header-media">
        <div class="header-section">
            <div class="row"> 
                <div class="col-md-4 col-lg-6 hidden-xs hidden-sm">
                    <h1>Bienvenido <strong>${usuario.firstName} ${usuario.lastName}</strong><br><small>Plataforma de gestión de entrenamiento por neurofeedback</small></h1>
                </div>
            </div>
        </div>
        <div style="overflow: hidden; justify-content: center; ">
            <g:img dir="images" file="main-header.jpg" style="position: absolute;top:-300px; ;width: 100%;transform: scaleX(-1);"/>
        </div>
    </div>
    <div class="row">
        <sec:ifAllGranted roles="ROLE_ADMIN">
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1" href="user/index">
                    <div class="widget-simple">
                        <div class="widget-icon pull-left themed-background animation-fadeIn">
                            <i class="fa fa-users"></i>
                        </div>
                        <h3 class="widget-content text-right animation-pullDown">
                            <strong>Usuarios </strong> registrados<br>
                            ${usuarios_registrados}
                        </h3>
                    </div>
                </a>
            </div>
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1" href="userTreatment/index">
                    <div class="widget-simple">
                        <div class="widget-icon pull-left themed-background-success animation-fadeIn">
                            <i class="fa fa-check"></i>
                        </div>
                        <h3 class="widget-content text-right animation-pullDown">
                            <strong>Tratamientos </strong> Finalizados Hoy<br> por pacientes ${tratamientos_hoy}
                        </h3>
                    </div>
                </a>
            </div>
        </sec:ifAllGranted>
        <sec:ifAllGranted roles="ROLE_PROFESSIONAL">
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1" href="userTreatment/index">
                    <div class="widget-simple">
                        <div class="widget-icon pull-left themed-background-success animation-fadeIn">
                            <i class="fa fa-check"></i>
                        </div>
                        <h3 class="widget-content text-right animation-pullDown">
                            <strong>Trat.</strong> Finalizados Hoy<br> por pacientes ${tratamientos_hoy}
                        </h3>
                    </div>
                </a>
            </div>
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1" href="user/index">
                    <div class="widget-simple">
                        <div class="widget-icon pull-left themed-background animation-fadeIn">
                            <i class="fa fa-users"></i>
                        </div>
                        <h3 class="widget-content text-right animation-pullDown">
                            <strong>Pacientes </strong> a cargo<br>
                            ${personas_a_cargo}
                        </h3>
                    </div>
                </a>
            </div>


        </sec:ifAllGranted>
        <sec:ifAllGranted roles="ROLE_PATIENT">
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1" href="userTreatment/index">
                    <div class="widget-simple">
                        <div class="widget-icon pull-left themed-background animation-fadeIn">
                            <i class="fa fa-plus"></i>
                        </div>
                        <h3 class="widget-content text-right animation-pullDown">
                            Nuevos <strong>tratamientos</strong><br>
                            <strong>${tratamientos_pendientes}</strong>
                        </h3>
                    </div>
                </a>
            </div>
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1" href="userTreatment/index">
                    <div class="widget-simple">
                        <div class="widget-icon pull-left themed-background-success animation-fadeIn">
                            <i class="fa fa-check"></i>
                        </div>
                        <h3 class="widget-content text-right animation-pullDown">
                            <strong>Trat.</strong> Finalizados<br>
                            ${tratamientos_finalizados}
                        </h3>
                    </div>
                </a>
            </div>
        </sec:ifAllGranted>

        <g:each var="usr" in="${usuarios}">
            <div class="col-md-4">
                <div class="widget">
                    <div class="widget-simple">
                        <div class="">
                            <g:img class="widget-image img-circle pull-left" dir="images" file="avatar.jpg" height="64" width="64"/>
                        </div>
                        <h4 class="widget-content">
                            <a href="user/show/${usr.id}" class="themed-color">
                                <strong>${usr.firstName} ${usr.lastName}</strong>
                            </a>
                            <small>${usr.email}</small>
                        </h4>
                    </div>
                    <div class="widget-extra">
                        <div class="row text-center <g:if test="${usr.role.id == 2}">themed-background-dark</g:if>
                                        <g:elseif test="${usr.role.id == 1}">
                                            themed-background-dark-autumn</g:elseif><g:else>themed-background-dark-amethyst</g:else>">
                            <div class="col-xs-4">
                                <h3 class="widget-content-light">
                                    <i class="fa fa-id-card"></i><br>
                                    <small>${usr.docNumber}</small>
                                </h3>
                            </div>
                            <div class="col-xs-4">
                                <h3 class="widget-content-light">
                                    <i class="fa fa-user"></i><br>
                                    <small>${usr.role.beauty}</small>
                                </h3>
                            </div>
                            <div class="col-xs-4">
                                <h3 class="widget-content-light">
                                    <i class="fas fa-caret-square-right"></i><br>
                                    <small>${usr.getTreatments().size}</small>
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </g:each>
        <g:each var="trat" in="${tratamientos}">
            <div class="col-md-4">
                <div class="widget">
                    <div class="widget-simple">
                        <h4 class="widget-content">
                            <a href="userTreatment/show/${trat.id}" class="themed-color">
                                <strong>${trat.treatment.name} ${trat.treatmentDate}</strong>
                            </a>
                            <small>${trat.treatment.description}</small>
                        </h4>
                    </div>
                    <div class="widget-extra">
                        <div class="row text-center <g:if test="${trat.status == 'Pending'}">themed-background-modern</g:if>
                                        <g:elseif test="${trat.status == 'Finished'}">
                                            themed-background-dark-autumn</g:elseif>
                                            <g:else>themed-background-success</g:else>">
                            <div class="col-xs-4">
                                <h3 class="widget-content-light">
                                    <i class="fa fa-info-circle"></i><br>
                                    <small>${trat.status}</small>
                                </h3>
                            </div>
                            <div class="col-xs-4">
                                <h3 class="widget-content-light">
                                    <i class="fa fa-clock"></i><br>
                                    <small>${trat.duration}</small>
                                </h3>
                            </div>
                            <div class="col-xs-4">
                                <h3 class="widget-content-light">
                                    <i class="fas fa-percent"></i><br>
                                    <small>${trat.effectiveness}</small>
                                </h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </g:each>
    </div>
</body>
</html>
