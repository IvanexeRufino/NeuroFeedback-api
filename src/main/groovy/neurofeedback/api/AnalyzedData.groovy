package neurofeedback.api

import org.apache.commons.math3.complex.Complex

class AnalyzedData {

    def frequency
    def spd
    def frequencies
    def analysisTime
    PowerBand powerBand
    List sourceData
    List visualizedData

    AnalyzedData(List originalData, int frequency) {
        this.analysisTime = 1000
        this.spd = []
        this.frequencies = []
        this.powerBand = new PowerBand()
        this.frequency = frequency
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
}
