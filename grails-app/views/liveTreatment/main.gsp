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
				Bienvenido a la plataforma de aministración del sistema de neurocare
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
    var chart;
    var i = 0;

    Highcharts.chart('containerMain', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.analysis], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Analyzed Data'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'Analyzed Data',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>

<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container1', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch1], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 1'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 1',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container2', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch2], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 2'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 2',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container3', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch3], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 3'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 3',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container4', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch4], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 4'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 4',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container5', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch5], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 5'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 5',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container6', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch6], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 6'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 6',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container7', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch7], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 7'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 7',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
<script type="text/javascript">
    var chart;
    var i = 0;

    Highcharts.chart('container8', {
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
                            url: '/liveTreatment/data/${params.id}',
                            type: 'get',
                            success: function (jsonArray) {
                                var x = (new Date()).getTime();
                                series[0].addPoint([x, jsonArray.ch8], true, true);
                            }
                        });
                    }, 500);
                }
            }
        },
        title: {
            text: 'Channel 8'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
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
            name: 'channel 8',
            data: (function () {
                // generate an array of random data
                var data = [],
                    time = (new Date()).getTime(),
                    i;

                for (i = -19; i <= 0; i += 1) {
                    data.push({
                        x: time + i * 1000,
                        y: 0
                    });
                }
                return data;
            }())
        }]
    });
</script>
</body>
</html>