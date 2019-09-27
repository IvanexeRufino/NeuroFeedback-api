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
                Total power:${(analyzedData.powerBand.totalPower) as Integer}<br>
                Delta power:${((analyzedData.powerBand.deltaPower / analyzedData.powerBand.totalPower)*100).round(2)}%<br>
                Theta power:${((analyzedData.powerBand.thetaPower / analyzedData.powerBand.totalPower)*100).round(2)}%<br>
                Alpha power:${((analyzedData.powerBand.alphaPower / analyzedData.powerBand.totalPower)*100).round(2)}%<br>
                Beta power:${((analyzedData.powerBand.betaPower / analyzedData.powerBand.totalPower)*100).round(2)}%<br>
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
    var frequencies = ${analyzedData.frequencies};
    var spd = ${analyzedData.spd};

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
                text: 'Frequency [Hz]'
            },
            max: frequencies.max,
            min: 0
        },
        yAxis: {
            max: spd.max,
            min: 0,
            title: {
                text: 'Power Spectral Density [μV²/HZ]'
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
            turboThreshold: spd.max,
            data: (function () {
                // generate an array of random data
                var data = [],
                    i;

                for (i = 0; i < spd.length; i += 1) {
                    data.push({
                        x: frequencies[i],
                        y: spd[i]
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