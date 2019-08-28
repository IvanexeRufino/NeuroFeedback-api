<!DOCTYPE html>
<html>
<head>
	<title>Iniciar Tratamiento</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <asset:stylesheet src="bootstrap.min.css"/>
    <asset:stylesheet src="tratamiento.css"/>
    <asset:javascript src="jquery-2.2.0.min.js"/>
    <asset:javascript src="bootstrap.js"/>
    <script src="https://use.fontawesome.com/fa5ce205b9.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
	  <div class="container">
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>                        
	      </button>
	      <a class="navbar-brand" href="#">NeuroCare</a>
	    </div>
	    <div class="collapse navbar-collapse" id="myNavbar">
	      <ul class="nav navbar-nav navbar-right">
	        <li><a href="#">Conectar Dispositivo</a></li>
	        <li><a href="#">Tratamiento</a></li>
	        <li><a href="#">Resultados</a></li>
	      </ul>
	    </div>
	  </div>
	</nav>
	<div class="container-fluid bg-3 text-center t-full-screen" style="min-height: 80vh;" id="div-conexion">    
		<div id="dispositivoNoConectado">
			<h3 class="margin">Conectar Dispositivo</h3><br>
	  		<div class="row">
			    <div class="col-sm-3"> 
			      	<p>Verifica la conexión Bluetooth</p>
	      			<img src="https://www.w3schools.com/bootstrap/birds1.jpg" class="img-responsive margin" style="width:100%" alt="Image">
	    		</div>
			    <div class="col-sm-3">
				    <p>Tocá el botón Comenzar</p>
				    <img src="https://www.w3schools.com/bootstrap/birds1.jpg" class="img-responsive margin" style="width:100%" alt="Image">
			    </div>
			    <div class="col-sm-3"> 
			        <p>Busca nuestro dispositivo</p>
			        <img src="https://www.w3schools.com/bootstrap/birds1.jpg" class="img-responsive margin" style="width:100%" alt="Image">
			    </div>
			    <div class="col-sm-3"> 
			      	<p>Al confirmar empezás el tratamiento</p>
	      			<img src="https://www.w3schools.com/bootstrap/birds1.jpg" class="img-responsive margin" style="width:100%" alt="Image">
	    		</div>
	    		<div class="col-md-12">
	    			<button class="btn btn-primary" id="btn_buscar_bt">Comenzar</button>
	    		</div>
	  		</div>
		</div>
  		<div id="dispositivoConectando" class="cargando" style="display: none;">
  			<div class="row" >
  			<h3 class="margin">Conectando Dispositivo</h3>
			  	<div class="loader" id="loader"></div>
  			</div>
  		</div>
  		<div id="dispositivoConectado" class="cargando" style="display: none;">
  			<div class="row" >
  			<h3 class="margin">Dispositivo Conectado</h3>
			  	<div class="loaded" id="loaded"><i class="fa fa-check-circle fa-5x"></i></div>
  			</div>
  		</div>
	</div>
	<div class="container-fluid bg-3 text-center " id="div-tratamiento">
		<h3 >Tratamiento en proceso</h3>
		<div  class="row">
			<div class="col-md-12">
				<div id="container"></div>
			</div>
			<br>
			<div class="col-md-12">
				<div>
					<button class="btn btn-primary" type="button" id="accuracy_bar"></button>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid bg-1 text-center resultados" id="div-resultados">    
  		<h3 class="margin">Resultados del entrenamiento</h3><br>
  		<div class="row">
		    <div class="col-sm-4">
			    <p>Resultado Anterior</p>
			    <ul>
			    	<li>1</li>
			    	<li>2</li>
			    	<li>3</li>
			    </ul>
		    </div>
		    <div class="col-sm-4"> 
		        <p>Resultado Tratamiento</p>
			    <ul>
			    	<li>1</li>
			    	<li>2</li>
			    	<li>3</li>
			    </ul>
		    </div>
		    <div class="col-sm-4"> 
		      	<p>Resultado Promedio</p>
			    <ul>
			    	<li>1</li>
			    	<li>2</li>
			    	<li>3</li>
			    </ul>
    		</div>
  		</div>
	</div>
	<footer class="container-fluid bg-4 text-center reducido">
	  	<p> <a href="https://google.com.ar">NeuroCare</a> Todos los derechos reservados</p> 
	</footer>
</body>
</html>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript">
document.addEventListener('DOMContentLoaded', function() {
  x = 0;
 chart = Highcharts.chart('container', {
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Live random data'
        },
        xAxis: {
        },
        yAxis: {
        },
        series: [{
            name: 'Random data',
            data: []
        }]
    });   
 }); 
</script>

<script type="text/javascript">
    var bluetoothDevice;
    var batteryLevelCharacteristic;

function hideExcept(show){
	$("#dispositivoNoConectado").fadeOut("slow");
	$("#dispositivoConectado").fadeOut("slow");
	$("#dispositivoConectando").fadeOut("slow");
	scroll(show);
	$("#"+show).show();
}
jQuery.fn.extend(
{
  scrollTo : function(speed, easing)
  {
    return this.each(function()
    {
      var targetOffset = $(this).offset().top;
      $('html,body').animate({scrollTop: targetOffset}, speed, easing);
    });
  }
});

function scroll(div){
	$("#"+div).scrollTo(500);
}
$("#btn_buscar_bt").click(function() {
    onReadBatteryLevelButtonClick();
    hideExcept("dispositivoConectando");
});
async function onReadBatteryLevelButtonClick() {
  try {
    if (!bluetoothDevice) {
      await requestDevice();
    }
    console.log('Leyendo Nivel de Batería...');
    await connectDeviceAndCacheCharacteristics();

    console.log('Leyendo Nivel de Batería...');
    await batteryLevelCharacteristic.readValue();

    onStartNotificationsButtonClick();
    hideExcept("dispositivoConectado");
    scroll("div-tratamiento");
  } catch(error) {
  	hideExcept("dispositivoNoConectado");
    console.log('Argh! ' + error);
  }
}

async function requestDevice() {
  console.log('Buscando dispositivos Bluetooth...');
  bluetoothDevice = await navigator.bluetooth.requestDevice({
   // filters: [...] <- Prefer filters to save energy & show relevant devices.
      acceptAllDevices: true,
      optionalServices: ['battery_service']});
  bluetoothDevice.addEventListener('gattserverdisconnected', onDisconnected);
}

async function connectDeviceAndCacheCharacteristics() {
  if (bluetoothDevice.gatt.connected && batteryLevelCharacteristic) {
    return;
  }

  console.log('Conectando a Servidor GATT ...');
  const server = await bluetoothDevice.gatt.connect();

  console.log('Obteniendo nivel de Bateria...');
  const service = await server.getPrimaryService('battery_service');

  console.log('Obteniendo caracteristica del nivel de bateria...');
  batteryLevelCharacteristic = await service.getCharacteristic('battery_level');

  batteryLevelCharacteristic.addEventListener('characteristicvaluechanged',
      handleBatteryLevelChanged);
}

/* This function will be called when `readValue` resolves and
 * characteristic value changes since `characteristicvaluechanged` event
 * listener has been added. */
function handleBatteryLevelChanged(event) {
  let batteryLevel = event.target.value.getUint8(0);
  console.log('> Battery Level is ' + batteryLevel + '%');  
  var series = chart.series[0];
  var shift = series.data.length > 20;
  chart.series[0].addPoint([x,batteryLevel], true, shift);
  x++;
  adjustAccuracy(batteryLevel);
}

function adjustAccuracy(value){
	if(value > 50){
		$("#accuracy_bar").css('background-color','rgb(255,0,0)');
	}else if( value < 50){
		$("#accuracy_bar").css('background-color','rgb(0,0,255)');
	}else{		
		$("#accuracy_bar").css('background-color','rgb(255,100,255)');

	}
		$("#accuracy_bar").animate({width:value*5},1000);
}
async function onStartNotificationsButtonClick() {
  try {
    console.log('Starting Battery Level Notifications...');
    await batteryLevelCharacteristic.startNotifications();

    console.log('> Notifications started');
  } catch(error) {
    console.log('Argh! ' + error);
  }
}

async function onStopNotificationsButtonClick() {
  try {
    console.log('Stopping Battery Level Notifications...');
    await batteryLevelCharacteristic.stopNotifications();

    console.log('> Notifications stopped');
  } catch(error) {
    console.log('Argh! ' + error);
  }
}

function onResetButtonClick() {
  if (batteryLevelCharacteristic) {
    batteryLevelCharacteristic.removeEventListener('characteristicvaluechanged',
        handleBatteryLevelChanged);
    batteryLevelCharacteristic = null;
  }
  // Note that it doesn't disconnect device.
  bluetoothDevice = null;
  console.log('> Bluetooth Device reset');
}

async function onDisconnected() {
  console.log('> Bluetooth Device disconnected');
  try {
    await connectDeviceAndCacheCharacteristics()
  } catch(error) {
    console.log('Argh! ' + error);
  }
}
</script>
