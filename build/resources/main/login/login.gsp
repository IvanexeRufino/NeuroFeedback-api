    <asset:stylesheet src="bootstrap.min.css"/>
    <asset:stylesheet src="plugins.css"/>
    <asset:stylesheet src="main.css"/>
    <asset:stylesheet src="style.css"/>
    <asset:stylesheet src="themes.css"/>
    <asset:stylesheet src="theme2.css"/>
    <asset:stylesheet src="custom.css"/>

<g:img dir="images" file="background.jpg" class="full-bg animation-pulseSlow"/>
<!-- END Login Full Background -->

<!-- Login Container -->
<div id="login-container" class="animation-fadeIn">
    <!-- Login Title -->
    <div class="login-title text-center">
        <h1><i class="fa fa-brain"></i> <strong>NeuroCare</strong><br><small>
        Por favor <strong>Inicie Sesión</strong> o <strong>Registrese</strong></small></h1>
    </div>
    <div class="block push-bit">
        <!-- Login Form -->
        <form action="autenticar" method="post" id="form-login" class="form-horizontal form-bordered form-control-borderless">
            <div class="form-group">
                <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-envelope"></i></span>
                        <input type="text" id="login-email" name="usuario" class="form-control input-lg" placeholder="Ingrese Mail o Usuario">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-asterisk"></i></span>
                        <input type="password" id="login-password" name="contrasenia" class="form-control input-lg" placeholder="Ingrese su Contraseña">
                    </div>
                </div>
            </div>

            <div class="form-group form-actions">
                <div class="col-xs-4">
                    <label class="switch switch-primary" data-toggle="tooltip" title="Remember Me?">
                        <input type="checkbox" id="login-remember-me" name="login-remember-me" checked>
                        <span></span>
                    </label>
                </div>
                <div class="col-xs-8 text-right">
                    <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-angle-right"></i> Iniciar Sesión</button>
                </div>
            </div>

            <!--
            <div class="form-group">
                <div class="col-xs-12 text-center">
                    <a href="javascript:void(0)" id="link-reminder-login"><small>Olvido su contraseña?</small></a> -
                    <a href="javascript:void(0)" id="link-register-login"><small>Crear una cuenta</small></a>
                </div>
            </div> 
        -->
        </form>
        <!-- END Login Form -->

        <!-- Reminder Form -->
        <form action="recuperarContrasenia" method="post" id="form-reminder" class="form-horizontal form-bordered form-control-borderless display-none">
            <div class="form-group">
                <div class="col-xs-12">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-envelope"></i></span>
                        <input type="text" id="reminder-email" name="reminder-email" class="form-control input-lg" placeholder="Email">
                    </div>
                </div>
            </div>
            <div class="form-group form-actions">
                <div class="col-xs-12 text-right">
                    <button type="submit" class="btn btn-sm btn-primary"><i class="fa fa-angle-right"></i> Reiniciar Contraseña</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 text-center">
                    <small>Recuerdas tu contraseña?</small> <a href="javascript:void(0)" id="link-reminder"><small>Iniciar Sesión</small></a>
                </div>
            </div>
        </form>
        <!-- END Reminder Form -->

        <!-- Register Form -->
        <form action="nuevoUsuario" method="post" id="form-register" class="form-horizontal form-bordered form-control-borderless display-none">
            <div class="form-group">
                <div class="col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-user"></i></span>
                        <input type="text" id="register-firstname" name="nombre" class="form-control input-lg" required="" placeholder="Nombre">
                    </div>
                </div>
                <div class="col-xs-6">
                	<div class="input-group">
                		<span class="input-group-addon"><i class="gi gi-user"></i></span>
                    	<input type="text" id="register-lastname" name="usuario" class="form-control input-lg" required placeholder="Usuario">
                	</div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon">
                        	<i class="gi gi-envelope"></i>
                        </span>
                        <input type="text" id="email" name="mail" class="form-control input-lg" placeholder="Email">
                    </div>
                </div>
                <div class="col-xs-6">

                    <div class="input-group">
                	<span class="input-group-addon">
                        	<i class="gi gi-iphone"></i>
                        </span>
                    <input type="text" id="register-telefono" name="telefono" class="form-control input-lg" placeholder="Telefono">
                	</div>
                </div>	
            </div>
            <div class="form-group">
                <div class="col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-asterisk"></i></span>
                        <input type="password" id="register-password" name="contrasenia" class="form-control input-lg" placeholder="Contraseña">
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-asterisk"></i></span>
                        <input type="password" id="register-password-verify" name="recontrasenia" class="form-control input-lg" placeholder="Repita su Contraseña">
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-home"></i></span>
                        <input type="text" id="register-password-verify" name="domicilio" class="form-control input-lg" placeholder="Domicilio">
                    </div>
                </div>

                <div class="col-xs-6">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="gi gi-search"></i></span>
                        <input type="text" id="register-password-verify" name="cuit" class="form-control input-lg" placeholder="CUIT o CUIL">
                    </div>
                </div>
            </div>
            <div class="form-group form-actions">
                
                <div class="col-xs-6 text-right">
                    <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-plus"></i> Registrar Cuenta</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-xs-12 text-center">
                    <small>Ya tienes una cuenta?</small> <a href="javascript:void(0)" id="link-register"><small>Inicia Sesión</small></a>
                </div>
            </div>
        </form>
        <!-- END Register Form -->
    </div>
    <!-- END Login Block -->
</div>
<script src="js/pages/login.js"></script>
<script>$(function(){ Login.init(); });</script>
