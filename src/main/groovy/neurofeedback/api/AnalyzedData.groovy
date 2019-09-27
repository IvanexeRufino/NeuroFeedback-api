package neurofeedback.api

import org.apache.commons.math3.complex.Complex

class AnalyzedData {

    def fs = (100 / 2)
    def spd
    def frequencies
    PowerBand powerBand

    AnalyzedData() {
        spd = []
        frequencies = []
        powerBand = new PowerBand()
    }

    def addComplex(Complex complex, frequencyIndex, nysquiSize) {
        def spectralPower = ((complex.abs() * complex.abs()) / 100000)
        def frecuency = frequencyIndex * (fs/(nysquiSize))

        spd.add(spectralPower)
        frequencies.add(frecuency)
        powerBand.addSpectralPower(spectralPower, frecuency)
    }
}
