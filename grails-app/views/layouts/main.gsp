<!DOCTYPE html>
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if IE 9]>         <html class="no-js lt-ie10"> <![endif]-->
<!--[if gt IE 9]><!--> 
<html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0">
        <asset:stylesheet src="bootstrap.min.css"/>
        <asset:stylesheet src="plugins.css"/>
        <asset:stylesheet src="main.css"/>
        <asset:stylesheet src="style.css"/>
        <asset:stylesheet src="themes.css"/>
        <asset:stylesheet src="theme2.css"/>
        <asset:stylesheet src="custom.css"/>
        <asset:stylesheet src="datatables.min.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">

        <asset:javascript src="jquery-2.2.0.min.js"/>
        <asset:javascript src="bootstrap.js"/>
        <asset:javascript src="plugins.js"/>
        <asset:javascript src="app.js"/>
        <asset:javascript src="dataTables.min.js"/>
        <title>NeuroCare</title>
    </head>
    <body>
    <div id="page-wrapper">
    <div id="page-container" class="enable-cookies  disable-menu-autoscroll footer-fixed sidebar-no-animations sidebar-partial sidebar-visible-lg">
        <!-- Alternative Sidebar -->
        <div id="sidebar-alt">
            <!-- Wrapper for scrolling functionality -->
            <div id="sidebar-alt-scroll">
                <!-- Sidebar Content -->
                <div class="sidebar-content">
                    
                </div>
                <!-- END Sidebar Content -->
            </div>
            <!-- END Wrapper for scrolling functionality -->
        </div>
        <!-- END Alternative Sidebar -->

        <!-- Main Sidebar -->
        <div id="sidebar">
            <!-- Wrapper for scrolling functionality -->
            <div id="sidebar-scroll">
                <!-- Sidebar Content -->
                <div class="sidebar-content">
                    <!-- Brand -->
                    <a href="" class="sidebar-brand">
                        <i class="gi gi-flash"></i>
                        <span class="sidebar-nav-mini-hide">
                                <strong>NeuroCare</strong>
                        </span>
                    </a>
                    <!-- END Brand -->

                    <div class="sidebar-section sidebar-user clearfix sidebar-nav-mini-hide">
                        <div class="sidebar-user-links" style="display: none;">
                            <a data-toggle="tooltip" data-placement="bottom" title="Perfil"><i class="gi gi-user"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Mensajes"><i class="gi gi-envelope"></i></a>
                            <a href="#" class="enable-tooltip" data-placement="bottom" title="Ajustes" onclick="$('#modal-user-settings').modal('show');"><i class="gi gi-cogwheel"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="Cerrar Sesión"><i class="gi gi-exit"></i></a>
                        </div>
                        <sec:ifLoggedIn>
                            <div class="sidebar-user-avatar">
                                <g:img dir="images" file="avatar.jpg"/>
                            </div>
                            <div class="sidebar-user-name">
                                <g:message message="${neurofeedback.currentUserProps(username: true)}"></g:message>
                            </div>
                        </sec:ifLoggedIn>
                    </div>

                    <!-- User Info -->
                    <ul class="sidebar-nav">
                        <sec:ifNotLoggedIn>
                            <g:link controller='login' action='auth'>Login</g:link>
                        </sec:ifNotLoggedIn>
                        <sec:ifLoggedIn>
                        </sec:ifLoggedIn>
                        <!-- PERDON LA VILLEREADA -->
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                                <g:if test="${c.getStaticPropertyValue('administrator', Boolean)}">
                                    <li class="controller">
                                        <g:link controller="${c.logicalPropertyName}">
                                            ${c.getStaticPropertyValue('adminFriendlyName', String)}
                                        </g:link>
                                    </li>
                                </g:if>
                            </g:each>
                        </sec:ifAllGranted>
                        <sec:ifAllGranted roles="ROLE_PROFESSIONAL">
                            <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                                <g:if test="${c.getStaticPropertyValue('professional', Boolean)}">
                                    <li class="controller">
                                        <g:link controller="${c.logicalPropertyName}">
                                            ${c.getStaticPropertyValue('friendlyName', String)}
                                        </g:link>
                                    </li>
                                </g:if>
                            </g:each>
                        </sec:ifAllGranted>
                        <sec:ifAllGranted roles="ROLE_PATIENT">
                        <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                            <g:if test="${c.getStaticPropertyValue('patient', Boolean)}">
                                <li class="controller">
                                    <g:link controller="${c.logicalPropertyName}">
                                        ${c.getStaticPropertyValue('friendlyName', String)}
                                    </g:link>
                                </li>
                            </g:if>
                        </g:each>
                        </sec:ifAllGranted>
                        <sec:ifLoggedIn>
                            <li class="controller">
                                <a href="/logoff">Logout</a>
                            </li>
                        </sec:ifLoggedIn>
                    </ul>
                    <!-- END Sidebar Navigation -->
                </div>
                <!-- END Sidebar Content -->
            </div>
            <!-- END Wrapper for scrolling functionality -->
        </div>
        <!-- END Main Sidebar -->

        <!-- Main Container -->
        <div id="main-container">
            <!-- Header -->
            <header class="navbar-fixed-navbar navbar-fixed">
                <!-- Navbar Header -->
                <div class="navbar-header">
                    <ul class="nav navbar-nav-custom pull-right visible-xs">
                        <li>
                            <a href="javascript:void(0)" data-toggle="collapse" data-target="#horizontal-menu-collapse">Menu</a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="App.sidebar('toggle-sidebar-alt');">
                                <i class="gi gi-share_alt"></i>
                                <span class="label label-primary label-indicator animation-floating">4</span>
                            </a>
                        </li>
                    </ul>
                    <!-- END Horizontal Menu Toggle + Alternative Sidebar Toggle Button -->

                    <!-- Main Sidebar Toggle Button -->
                    <ul class="nav navbar-nav-custom">
                        <li>
                            <a href="javascript:void(0)" onclick="App.sidebar('toggle-sidebar');this.blur();">
                                <i class="fa fa-bars fa-fw"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- END Main Sidebar Toggle Button -->
                </div>
                <!-- END Navbar Header -->

                <!-- Alternative Sidebar Toggle Button, Visible only in large screens (> 767px) -->
                <ul class="nav navbar-nav-custom pull-right hidden-xs">
                    <li>
                    </li>
                </ul>
                <div id="horizontal-menu-collapse" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="/">Inicio</a>
                        </li>
                    </ul>
                </div>
            </header>
            <div id="page-content">
                <g:layoutBody/>
            </div>
            <footer class="clearfix">
                <div class="pull-right">
                    NeuroCare
                </div>
                <div class="pull-left">
                    <span id="year-copy"></span> &copy;1.0</a>
                </div>
            </footer>
            <!-- END Footer -->
        </div>
        <!-- END Main Container -->
    </div>
    <!-- END Page Container -->
</div>
<!-- END Page Wrapper -->

<!-- Scroll to top link, initialized in js/app.js - scrollToTop() -->
<a href="#" id="to-top"><i class="fa fa-angle-double-up"></i></a>

<!-- User Settings, modal which opens from Settings link (found in top right user menu) and the Cog link (found in sidebar user info) -->
    <div id="modal-user-settings" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header text-center">
                    <h2 class="modal-title"><i class="fa fa-pencil"></i> Configuración</h2>
                </div>
                
            </div>
        </div>
    </div>
    
    </body>
</html>