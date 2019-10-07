package neurofeedback.api

class ChannelConfig {

    Channel channel
    FrequencyBand frequencyBand

    String feedbackType
    double minTotalPowerValue
    double maxTotalPowerValue
    double minAverageFrequencyPowerValue
    double maxAverageFrequencyPowerValue

    ChannelConfig(Channel channel, FrequencyBand fb, String feedbackType, double minTotalPowerValue, double maxTotalPowerValue, double minAverageFrequencyPowerValue, double maxAverageFrequencyPowerValue) {
        this.channel = channel
        this.frequencyBand = fb
        this.feedbackType = feedbackType
        this.minTotalPowerValue = minTotalPowerValue
        this.maxTotalPowerValue = maxTotalPowerValue
        this.minAverageFrequencyPowerValue = minAverageFrequencyPowerValue
        this.maxAverageFrequencyPowerValue = maxAverageFrequencyPowerValue
    }

    Map<String, String> evaluate(AnalyzedData analyzedData) {
        Map analysis = [:]
        analysis["Average band power"] = evaluateSignal(analyzedData.powerBand.averageBandPower, minTotalPowerValue, maxTotalPowerValue)

        switch(frequencyBand.name) {
            case "ALPHA":
                analysis["Frequency contribution"] = evaluateSignal(analyzedData.powerBand.alphaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
            case "BETA":
                analysis["Frequency contribution"] = evaluateSignal(analyzedData.powerBand.betaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
            case "DELTA":
                analysis["Frequency contribution"] = evaluateSignal(analyzedData.powerBand.deltaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
            case "THETA":
                analysis["Frequency contribution"] = evaluateSignal(analyzedData.powerBand.thetaPowerContribution, minAverageFrequencyPowerValue, maxAverageFrequencyPowerValue)
                break
        }

        return analysis
    }

    String evaluateSignal(value, min, max) {
        if((value < max) && (min < value)) {
            return feedbackType
        } else {
            return "Neutral"
        }
    }

    def toJson() {
        [channel_name: channel.name, channel_pos: channel.pos]
    }

    static constraints = {
    }
}
