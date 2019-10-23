package neurofeedback.api

class PowerBand {

    def sampleSize = 0
    def totalPower = 0
    def alphaPower = 0
    def betaPower = 0
    def deltaPower = 0
    def thetaPower = 0


    def addSpectralPower(spectralPower, frequency) {
        totalPower += spectralPower
        sampleSize++

        if (frequency >= 0.5 && frequency < 4) {
            deltaPower += spectralPower
        } else if (frequency >= 4 && frequency < 8) {
            thetaPower += spectralPower
        } else if (frequency >= 8 && frequency < 12) {
            alphaPower += spectralPower
        } else if (frequency >= 12 && frequency < 30) {
            betaPower += spectralPower
        }
    }

    def getAverageBandPower() {
        return totalPower / sampleSize
    }

    def getAlphaPowerContribution() {
        return (alphaPower / totalPower) * 100
    }

    def getBetaPowerContribution() {
        return (betaPower / totalPower) * 100
    }

    def getDeltaPowerContribution() {
        return (deltaPower / totalPower) * 100
    }

    def getThetaPowerContribution() {
        return (thetaPower / totalPower) * 100
    }
    def toJson(){
        "{\"sampleSize\":"+sampleSize+",\"totalPower\":"+totalPower+",\"alphaPower\":"+alphaPower+",\"betaPower\":"+betaPower+",\"deltaPower\":"+deltaPower+",\"thetaPower\":"+thetaPower+"}"
    }
}
