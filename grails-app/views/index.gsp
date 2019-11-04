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
                    <h1>Bienvenido <strong>${usuario.firstName} ${usuario.lastName}</strong><br><small>A la plataforma de gestión de entrenamiento por neurofeedback</small></h1>
                </div>
            </div>
        </div>
        <g:img dir="images" file="main-header.jpg" style="background-size:no-repeat"/>
    </div>
    <div class="row">
        <g:if test="${tratamientos_pendientes != 0}">
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1">
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
        </g:if>
        <g:if test="${tratamientos_finalizados != 0}">
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1">
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
        </g:if>
        <g:if test="${tratamientos_hoy != 0 && tratamientos_finalizados == 0}">
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1">
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
        </g:if>
        <g:if test="${personas_a_cargo != 0}">
            <div class="col-sm-6 col-lg-6">
                <a class="widget widget-hover-effect1">
                    <div class="widget-simple">
                        <div class="widget-icon pull-left themed-background animation-fadeIn">
                            <i class="fa fa-check"></i>
                        </div>
                        <h3 class="widget-content text-right animation-pullDown">
                            <strong>Pacientes </strong> a cargo<br>
                            ${personas_a_cargo}
                        </h3>
                    </div>
                </a>
            </div>
        </g:if>
    </div>



    <div id="container">
        
    </div>
<script src="https://code.highcharts.com/highcharts.js"></script>

<script type="text/javascript">
    var chart;
    var i = 0; 
    
Highcharts.chart('container', {
    chart: {
        backgroundColor: 'transparent',
        type: 'spline',
        animation: Highcharts.svg, // don't animate in old IE
        marginRight: 10,
        events: {
            load: function () {

                // set up the updating of the chart each second
                var series = this.series[0];
                setInterval(function () {
                    var x = (new Date()).getTime(), // current time
                        y = Math.random();
                    series.addPoint([x, y], true, true);
                }, 500);
            }
        }
    },
    title: {
        text: 'Ondas Cerebrales en vivo'
    },
    xAxis: {
        type: 'datetime',
        tickPixelInterval: 150
    },
    yAxis: {
        title: {
            text: 'Value'
        },
        plotLines: [{
            value: 0,
            width: 1,
            color: '#808080'
        }]
    },
    tooltip: {
        formatter: function () {
            return '<b>' + this.series.name + '</b><br/>' +
                Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' 
+
                Highcharts.numberFormat(this.y, 2);
        }
    },
    legend: {
        enabled: false
    },
    exporting: {
        enabled: false
    },
    series: [{
        name: 'Datos cerebrales',
        data: (function () {
            // generate an array of random data
            var data = [],
                time = (new Date()).getTime(),
                i;

            for (i = -19; i <= 0; i += 1) {
                data.push({
                    x: time + i * 1000,
                    y: Math.random()
                });
            }
            return data;
        }())
    }]
    });

</script>

</body>
</html>
