package neurofeedback.api

class ChannelConfig {

    Channel channel
    FrequencyBand frequencyBand
    int pos
    double minTotalPowerValue
    double maxTotalPowerValue
    double minAverageFrequencyPowerValue
    double maxAverageFrequencyPowerValue

    ChannelConfig(Channel channel, FrequencyBand fb, int pos, double minTotalPowerValue, double maxTotalPowerValue, double minAverageFrequencyPowerValue, double maxAverageFrequencyPowerValue) {
        this.channel = channel
        this.frequencyBand = fb
        this.pos = pos
        this.minTotalPowerValue = minTotalPowerValue
        this.maxTotalPowerValue = maxTotalPowerValue
        this.minAverageFrequencyPowerValue = minAverageFrequencyPowerValue
        this.maxAverageFrequencyPowerValue = maxAverageFrequencyPowerValue
    }

    Map<String, Boolean> evaluate(AnalyzedData analyzedData) {
        Map analysis = [:]
        analysis["Total power"] = isValueBetween(analyzedData.powerBand.averageBandPower, minTotalPowerValue, maxTotalPowerValue)

        switch(frequencyBand.name) {
            case "ALPHA":
                analysis["Average band power"] = isValueBetween(analyzedData.powerBand.alphaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
            case "BETA":
                analysis["Average band power"] = isValueBetween(analyzedData.powerBand.betaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
            case "DELTA":
                analysis["Average band power"] = isValueBetween(analyzedData.powerBand.deltaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
            case "THETA":
                analysis["Average band power"] = isValueBetween(analyzedData.powerBand.thetaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
        }

        return analysis
    }

    boolean isValueBetween(value, min, max) {
        return (value < max) && (min < value)
    }

    static constraints = {
    }
}
