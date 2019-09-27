<!doctype html>
<html>
<head>
	<meta name="layout" content="main"/>
</head>
<body>
<content tag="nav">
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
		<ul class="dropdown-menu">
			<li><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
			<li><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
			<li><a href="#">App version:
				<g:meta name="info.app.version"/></a>
			</li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Grails version:
				<g:meta name="info.app.grailsVersion"/></a>
			</li>
			<li><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
			<li><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
		<ul class="dropdown-menu">
			<li><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
			<li><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
			<li><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
			<li><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
		</ul>
	</li>
	<li class="dropdown">
		<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
		<ul class="dropdown-menu">
			<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
				<li><a href="#">${plugin.name} - ${plugin.version}</a></li>
			</g:each>
		</ul>
	</li>
</content>

<div class="row">
	<div class="col-md-12">
		<div class="row">
			<h1>NeuroCare</h1>
            <p>
                Total power:${(pb['Total'].sum()) as Integer}<br>
                Delta power:${((pb['Delta'].sum() / pb['Total'].sum())*100).round(2)}%<br>
                Theta power:${((pb['Theta'].sum() / pb['Total'].sum())*100).round(2)}%<br>
                Alpha power:${((pb['Alpha'].sum() / pb['Total'].sum())*100).round(2)}%<br>
                Beta power:${((pb['Beta'].sum() / pb['Total'].sum())*100).round(2)}%<br>
            </p>
		</div>
		<div class="row">
            <div id="containerMain" style="height: 350px"></div>
			<div class="column">
				<div id="container1" style="height: 250px; width: 550px; display: inline-block"></div>
				<div id="container2" style="height: 250px; width: 550px; display: inline-block"></div>
			</div>
			<div class="column">
				<div id="container3" style="height: 250px; width: 550px; display: inline-block"></div>
				<div id="container4" style="height: 250px; width: 550px; display: inline-block"></div>
			</div>
			<div class="column">
				<div id="container5" style="height: 250px; width: 550px; display: inline-block"></div>
				<div id="container6" style="height: 250px; width: 550px; display: inline-block"></div>
			</div>
			<div class="column">
				<div id="container7" style="height: 250px; width: 550px; display: inline-block"></div>
				<div id="container8" style="height: 250px; width: 550px; display: inline-block"></div>
			</div>
		</div>
	</div>
</div>
<script src="https://code.highcharts.com/highcharts.js"></script>

<script type="text/javascript">
    Highcharts.chart('containerMain', {
        chart: {
            backgroundColor: 'transparent',
            animation: false,
            marginRight: 10,
            events: {
                load: function () {
                }
            }
        },
        title: {
            text: 'Analyzed Data'
        },
        xAxis: {
            title: {
                text: 'Frequencies'
            },
            max: "${freqs}".max,
            min: 0
        },
        yAxis: {
            max: "${dataToload}".max,
            min: 0,
            title: {
                text: 'Power Spectral Density'
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
            name: 'Analyzed data',
            turboThreshold: 3000,
            data: (function () {
                // generate an array of random data
                var data = [],
                    i;

                var frequencies = ${freqs};
                var analysisData = ${dataToload};

                for (i = 0; i < analysisData.length; i += 1) {
                    data.push({
                        x: frequencies[i],
                        y: analysisData[i]
                    });
                }
                return data;
            }())
        }],
        annotations: [{
            labelOptions: {
                backgroundColor: 'rgba(255,255,255,0.5)',
                verticalAlign: 'top',
                y: 15
            },
            labels: [{
                point: {
                    xAxis: 0,
                    yAxis: 0,
                    x: 27.98,
                    y: 255
                },
                text: 'Arbois'
            }]
        }]
    });

    createHighChart('container1', 1);
    createHighChart('container2', 2);
    createHighChart('container3', 3);
    createHighChart('container4', 4);
    createHighChart('container5', 5);
    createHighChart('container6', 6);
    createHighChart('container7', 7);
    createHighChart('container8', 8);

    function createHighChart(container, channel_number) {
        Highcharts.chart(container, {
            chart: {
                backgroundColor: 'transparent',
                type: 'spline',
                animation: Highcharts.svg, // don't animate in old IE
                marginRight: 10,
                events: {
                    load: function () {
                        // set up the updating of the chart each second
                        var series = this.series;
                        setInterval(function () {
                            $.ajax({
                                url: '/liveTreatment/data/${params.id}?channel='+channel_number,
                                type: 'get',
                                success: function (json) {
                                    var x = (new Date()).getTime();
                                    series[0].addPoint([x, json['value']], true, true);
                                }
                            });
                        }, 5000);
                    }
                }
            },
            title: {
                text: 'Channel ' + channel_number
            },
            xAxis: {
                tickAmount: 100,
                type: 'datetime'
            },
            yAxis: {
                max: 50,
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
                name: 'channel ' +  channel_number,
                data: (function () {
                    // generate an array of random data
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -499; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: 0
                        });
                    }
                    return data;
                }())
            }]
        })
    }

</script>
</body>
</html>