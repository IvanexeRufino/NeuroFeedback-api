<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'userTreatment.label', default: 'userTreatment')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>

</head>
<body>
    <style type="text/css">
        .hoverable:hover{
            cursor: pointer;
            background-color: #d0e8e8;
        }
    </style>
    <div class="content-header" role="navigation">
        <ul class="nav-horizontal text-center">
            <li><a class="home" href="${createLink(uri: '/')}"><i class="fa fa-home"></i><g:message code="default.home.label"/></a></li>
        </ul>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div class="block">
                <div class="block-title">
                    <h1>Información del Tratamiento</h1>
                </div>
                <div id="generalChart">
                    
                </div>
                <ul style="list-style: none; font-size: 12px;text-align: center;" >
                    <li>Duración: ${tratamiento.duration}</li>
                    <li style="display: none;">Estado: <div id="treatment_status">${tratamiento.status}</div></li>
                    <li>Fecha Inicio: ${tratamiento.treatmentDate}</li>
                </ul>
            </div>
        </div>
        <div class="col-md-6">
            <div class="widget">
                <div class="widget-extra themed-background-dark">
                    <h3 class="widget-content-light">
                        Tratamientos similares de <strong>${user.firstName} ${user.lastName}</strong>
                    </h3>
                </div>
                <div class="widget-extra">
                    <!-- Timeline Content -->
                    <div class="timeline">
                        <ul class="timeline-list">
                            <g:each var="htratamientos" in="${tratamientos}">
                                    <g:if  test="${htratamientos.id.equals(tratamiento.id)}">
                                        <li class="active hoverable" style="background-color: #d0e8e8">
                                    </g:if>
                                    <g:else>
                                        <li class="hoverable">
                                    </g:else>
                                    <g:if test="${htratamientos.effectiveness < 50}">
                                        <div class="timeline-icon"><i class="fa fa-times"></i></div>
                                    </g:if>
                                    <g:else>
                                        <div class="timeline-icon"><i class="fa fa-check"></i></div>
                                    </g:else>
                                    <div class="timeline-time"><small>${htratamientos.treatmentDate} </small></div>
                                    <div class="timeline-content">
                                        <p class="push-bit">Duración: <strong>${htratamientos.duration}</strong></p>
                                        <p class="push-bit">Efectividad: <strong>${htratamientos.effectiveness}</strong> </p>
                                        <p class="push-bit"><a href="/userTreatment/history/${htratamientos.id}" class="btn btn-xs btn-info"><i class="fa fa-eye"></i></a></p>
                                    </div>
                                </li>
                            </g:each>
                        </ul>
                    </div>
                    <!-- END Timeline Content -->
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="widget">
                <div class="widget-extra themed-background-dark">
                    <h3 class="widget-content-light">
                        <strong>Canales </strong>utilizados en el tratamiento
                    </h3>
                </div>
                <div class="widget-extra">
                    <!-- Timeline Content -->
                    <div class="table-responsive">
                        <table class="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th>Canal</th>
                                    <th>Valor Mínimo</th>
                                    <th>Valor Máximo</th>
                                    <th>Promedio Obtenido</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <g:each var="canal" in="${canales}" status="i">
                                    
                                   
                                    <tr class="hover" data-placement="top" data-toggle="popover" data-container="body" data-html="true" data-id="${canal.channel.name}">
                                        <div id="popover-content${canal.channel.name}" style="display: none;">
                                            <g:img dir="images"  width="200" height="200" file="channels/channel${canal.channel.name}.png"/>
                                        </div>
                                        <td><strong>${canal.channel.name}</strong></td>
                                        <td>${canal.minAverageFrequencyPowerValue}</td>
                                        <td>${canal.maxAverageFrequencyPowerValue}</td>
                                        <td>${promedio_canales[i].substring(0,7)}</td>
                                    </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>
                    <!-- END Timeline Content -->
                </div>
            </div>
        </div>
        <div class="col-md-12" id="historial_tratamiento" >   
            <div class="block">  
                <div class="block-title">
                    <h2>Reproducción del tratamiento</h2>   
                </div>
                <div id="chart_history">   
                </div>
            </div>
        </div>
    </div>


<script src="https://code.highcharts.com/stock/highstock.js"></script>
<script type="text/javascript">

Highcharts.chart('generalChart', {
        chart: {
            backgroundColor: 'transparent',
            type: 'pie',
            animation: Highcharts.svg,
            marginRight: 10,
        },
        title: {
            text: 'Resultado Tratamiento'
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '{point.percentage:.1f} %'
                }
            }
        },
        series: [{
            name: 'Efectividad',
            colorByPoint: true,
            data: [{
                name: 'Positiva',
                y: ${tratamiento.effectiveness},
                color:"#90ED7D"
            }, {
                name: 'Negativa',
                y: ${100 - tratamiento.effectiveness},
                color:"#F45B5B"
            }]
        }]
    });

    window.addEventListener('load', function () {
        if($("#treatment_status").html()=="Finished"){
            $.ajax({
                url:"../../trackSession/treatmentHistory",
                data:{id:${tratamiento.id}},
                type:"POST",
                success:function(result){
                    console.log(result);
                    graficar(result);
                    console.log(result);
                    $("#historial_tratamiento").show();
                }
            })  
          
        }
    })
    
    function graficar(data){
        //console.log(data);
        //var points = JSON.parse(data)[0].visualizedData;

        data = (JSON.parse(data));
        Highcharts.chart('chart_history', {
            chart: {
                backgroundColor: 'transparent',
                type: 'spline',
                animation: Highcharts.svg,
                scrollbar:{
                    enabled:true
                }
            },
            title: {
            text: 'Entrenamiento Finalizado'
            },
            xAxis: {
                min: 0,
                max: 25,
                scrollbar: {
                    enabled: true
                }
            },
            yAxis: {
                title: {
                    text: 'Hz'
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
                        Highcharts.dateFormat('%H:%M:%S', this.x) + '<br/>'
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
            series: createSeries(data)
        });

    }

    function createSeries(data){
        var series = [];
        $.each(data, function( index, value ) {
            var newSeries = {name:value.channelName,data:convert(value.visualizedData),type:'spline',tooltip:{valueDecimals:2}};
            series.push(newSeries);
        });
        return series;
    }

    function convert(data){
        var i = 0;
        var result = data.map(function(a) {
          i++;return [i,a.y];
        });
        return result;
    }

    $("[data-toggle=popover]").popover({
        html: true, 
        trigger: "hover",
        content: function() {
              return $('#popover-content'+$(this).data("id")).html();
            }
    });
</script>
</body>
</html>