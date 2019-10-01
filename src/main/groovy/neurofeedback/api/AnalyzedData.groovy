package neurofeedback.api

import org.apache.commons.math3.complex.Complex

class AnalyzedData {

    def fs = (100 / 2)
    def spd
    def frequencies
    def sampleSize
    PowerBand powerBand
    List sourceData

    AnalyzedData(List originalData) {
        spd = []
        frequencies = []
        powerBand = new PowerBand()
        this.sampleSize = originalData.size() / 2
        this.sourceData = getMappedSourceData(originalData)
    }

    def addComplex(Complex complex, frequencyIndex) {
        def spectralPower = ((complex.abs() * complex.abs()) / 100000)
        def frecuency = frequencyIndex * (fs/(sampleSize))

        spd.add(spectralPower)
        frequencies.add(frecuency)
        powerBand.addSpectralPower(spectralPower, frecuency)
    }

    def updateAnalysis(AnalyzedData updated) {
        this.spd = updated.spd
        this.frequencies = updated.frequencies
        this.powerBand = updated.powerBand

        this.sourceData += updated.sourceData
    }

    List getMappedSourceData(List originalData) {
        def x = (new Date()).getTime() - 1000
        def accum = 0
        Point point
        def acumulativeData = []

        for (int i = 0; i < originalData.size(); i += 1) {
            point = new Point(x + accum, originalData[i])
            accum += 1000/(this.sampleSize * 2)
            acumulativeData.add(point)
        }

        return acumulativeData
    }
}
