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
                    <li>Estado: ${tratamiento.status}</li>
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
                                        <p class="push-bit">Estado: <strong>${htratamientos.status}</strong></p>
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
        <div class="col-md-12">   
            <div class="block">  
                <div class="block-title">
                    <h2>Reproducción del tratamiento</h2>   
                </div>
                <div id="realTime">   
                </div>
            </div>
        </div>
    </div>
<script src="https://code.highcharts.com/highcharts.js"></script>
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
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
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
</script>
</body>
</html>