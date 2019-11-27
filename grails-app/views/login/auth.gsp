    <asset:stylesheet src="bootstrap.min.css"/>
    <asset:stylesheet src="plugins.css"/>
    <asset:stylesheet src="main.css"/>
    <asset:stylesheet src="style.css"/>
    <asset:stylesheet src="themes.css"/>
    <asset:stylesheet src="theme2.css"/>
    <asset:stylesheet src="custom.css"/>
        <title>NeuroCare</title>

<g:img dir="images" file="background.jpg" class="full-bg animation-pulseSlow"/>
<!-- END Login Full Background -->

<!-- Login Container -->
<div id="login-container" class="animation-fadeIn">
    <!-- Login Title -->
    <div class="login-title text-center">
        <h1><i class="fa fa-brain"></i> <strong>NeuroCare</strong><br><small>
        Por favor <strong>Inicie Sesión</strong></small></h1>
    </div>
    <div class="block push-bit">
		<s2ui:form type='login' class="form-horizontal form-bordered form-control-borderless" focus='username'>
			<div class="sign-in"> 
				<div class="form-group">
	                <div class="col-xs-12">
	                    <div class="input-group">
	                        <span class="input-group-addon">Usuario</span>
	                        <input type="text" name="${securityConfig.apf.usernameParameter}" id="username" class='formLogin' size="20"/>
	                    </div>
	                </div>
	            </div>
	            <div class="form-group">
	                <div class="col-xs-12">
	                    <div class="input-group">
	                        <span class="input-group-addon">Contraseña</span>
	                        <input type="password" name="${securityConfig.apf.passwordParameter}" id="password" class="formLogin" size="20"/>
	                    </div>
	                </div>
	            </div>


	            <div class="form-group form-actions">
	                <div class="col-xs-4"> 
	                	 <label class="switch switch-primary" data-toggle="tooltip" title="Recordar usuario?">
	                        <input type="checkbox" id="login-remember-me" name="${securityConfig.rememberMe.parameter}" checked>
	                        <span></span>
	                    </label>
	                </div>
	                <div class="col-xs-8 text-right">
	                	<button type="submit" class="btn btn-sm btn-primary" id="loginButton">Inicia Sesión</button>
						
	                </div>
	            </div>
						
			</div>
		</s2ui:form>
    </div>
    <!-- END Login Block -->
</div>
