package neurofeedback.api

import org.apache.commons.math3.complex.Complex

class AnalyzedData {

    def frequency
    def spd
    def frequencies
    def analysisTime

    String channelName
    PowerBand powerBand
    List sourceData
    List visualizedData

    AnalyzedData(String channelName, List originalData, int frequency) {
        this.frequency = frequency
        this.spd = []
        this.frequencies = []
        this.analysisTime = 1000

        this.channelName = channelName
        this.powerBand = new PowerBand()
        this.sourceData = originalData
        this.visualizedData = getMappedSourceData(originalData)
    }

    def addComplex(Complex complex, frequencyIndex) {
        def spectralPower = ((complex.abs() * complex.abs()) / 1000000)

        spd.add(spectralPower)
        frequencies.add(frequencyIndex)
        powerBand.addSpectralPower(spectralPower, frequencyIndex)
    }

    def updateAnalysis(AnalyzedData updated) {
        this.spd = updated.spd
        this.frequencies = updated.frequencies
        this.powerBand = updated.powerBand

        this.visualizedData += updated.visualizedData
    }

    List getMappedSourceData(List originalData) {
        def x = (new Date()).getTime() - this.analysisTime
        def accum = 0
        def acumulativeData = []
        Point point

        for (int i = 0; i < originalData.size(); i += 1) {
            point = new Point(x + accum, originalData[i])
            accum += this.analysisTime/(this.frequency)
            acumulativeData.add(point)
        }

        return acumulativeData
    }

    def toJson(){
        def aux= "[ "
        visualizedData.each{aux+="${it.toJson()}  ,"}
        aux = aux.substring(0, aux.length() - 1)
        aux += "]  "
        return "{"+
            "\"frequency\":"+frequency+
            ",\"spd\":"+spd+
            ",\"frequencies\":"+frequencies+
            ",\"analysisTime\":"+analysisTime+
            ",\"channelName\":\""+channelName+"\""+
            ",\"powerBand\":"+powerBand.toJson()+
            ",\"sourceData\":"+sourceData+
            ",\"visualizedData\":"+aux+
        "}  "

    }
}
