<!doctype html>
<html>
<head>
	<meta name="layout" content="main"/>
</head>
<body>
<div class="content-header">
    <div class="header-section">
        <i class="fas fa-brain"></i><h1>NeuroCare</h1>
    </div>
</div>
<div class="row">
	<div class="col-md-12">
		<div class="row">
            <div class="col-md-12 block">
                <div id="containerMain" style="height: 350px"></div>
            </div> 
            <g:set var="counter" value="${1}"/>
            <g:while test="${counter < analyzedDatasCount+1}">
                <div class="col-md-6 block">
                    <div id="container${counter}" style="height: 250px; width: 550px; display: inline-block"></div>
                </div>
                <g:set var="counter" value="${counter+1}"/>
            </g:while>
		</div>
	</div>
</div>
<script src="https://code.highcharts.com/highcharts.js"></script>

<script type="text/javascript">

    /** ******************************************************************** **/
    /**              Creating high chart for EEG data                        **/
    /** ******************************************************************** **/

    function createAjax(series, channel_number) {
        setInterval(function () {
            $.ajax({
                url: '/liveTreatment/data/${params.id}?channel=' + channel_number,
                type: 'get',
                success: function (json) {
                    var source = json.visualizedData;
                    series.name = json.channelName;
                    series.setData(source, true, false, false);
                }
            });
        }, 1000);
    }

    Highcharts.chart('containerMain', {
        chart: {
            backgroundColor: 'transparent',
            type: 'spline',
            animation: Highcharts.svg,
            marginRight: 10,
            events: {
                load: function () {
                    for(var i = 0; i < ${analyzedDatasCount}; i++) {
                        createAjax(this.series[i], i)
                    }
                }
            }
        },
        title: {
            text: 'Tratamiento en vivo'
        },
        xAxis: {
            tickAmount: 100,
            type: 'datetime'
        },
        yAxis: {
            title: {
                text: 'micro Volts [mV]'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }],
            max: 2000,
            min: -2000
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
        series: getSeries()
    });

    function getSeries() {
        var series = [];

        for(var k = 1; k <= ${analyzedDatasCount}; k++){
            series.push({
                name: 'channel ' + k,
                data: (function () {
                    var data = [],
                        time = (new Date()).getTime(),
                        i;

                    for (i = -10; i <= 0; i += 1) {
                        data.push({
                            x: time + i * 1000,
                            y: k
                        });
                    }
                    return data;
                }()),
                turboThreshold: 10000000
            });
        }

        return series;
    }

    /** ******************************************************************** **/
    /**              Creating high chart for analyzed data                   **/
    /** ******************************************************************** **/

    createHighCharts(${analyzedDatasCount});

    function createHighCharts(quantity) {

        for (var i = 1; i <= quantity; i++) {
            createHighChart(i);
        }
    }

    function createHighChart(channel_number) {
        Highcharts.chart('container' + channel_number, {
            chart: {
                type: 'line',
                backgroundColor: 'transparent',
                animation: false,
                events: {
                    load: function () {
                        var chart = this;
                        setInterval(function () {
                            $.ajax({
                                url: '/liveTreatment/data/${params.id}?channel=' + channel_number,
                                type: 'get',
                                success: function (json) {

                                    var powerBand = json.powerBand;

                                    chart.legend.update(getLegend(powerBand.totalPower, powerBand.alphaPower,
                                        powerBand.betaPower, powerBand.deltaPower, powerBand.thetaPower));

                                    chart.title.update({
                                        text: 'Datos analizados ' + json.channelName
                                    });

                                    chart.series[0].update({
                                        data: (function () {
                                            var data = [], i;
                                            for (i = 0; i < json.spd.length; i += 1) {
                                                data.push({
                                                    x: json.frequencies[i],
                                                    y: json.spd[i]
                                                });
                                            }
                                            return data;
                                        }()),
                                        turboThreshold: json.spd.max
                                    }, true, true);
                                }
                            });
                        }, 1000);
                    }
                }
            },
            title: {
                text: 'Datos analizados'
            },
            xAxis: {
                title: {
                    text: 'Frecuencia [Hz]'
                }
            },
            yAxis: {
                title: {
                    text: 'PSD [μV²/HZ]'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }],
                min:0,
                max:250
            },
            legend: getLegend(0,0,0,0,0),
            series: [{
                name: 'Datos analizados',
                data: []
            }]
        });
    }

    function getLegend(totalPower, alpha, beta, delta, theta) {
        return {
            layout: 'vertical',
            backgroundColor: '#FFFFFF',
            floating: true,
            align: 'right',
            verticalAlign: 'top',
            x: 0,
            y: 0,
            labelFormatter: function () {
                return "Total power:" + (totalPower.toFixed(0)) + "<br>\n" +
                    "                    Delta power:" + ((delta / (totalPower ? totalPower : 1)) * 100).toFixed(2) + "%<br>\n" +
                    "                    Theta power:" + ((theta / (totalPower ? totalPower : 1)) * 100).toFixed(2) + "%<br>\n" +
                    "                    Alpha power:" + ((alpha / (totalPower ? totalPower : 1)) * 100).toFixed(2) + "%<br>\n" +
                    "                    Beta power:" + ((beta / (totalPower ? totalPower : 1)) * 100).toFixed(2) + "%<br>";
            }
        };
    }

</script>
</body>
</html>