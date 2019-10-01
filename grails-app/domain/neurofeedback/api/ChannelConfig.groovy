package neurofeedback.api

class ChannelConfig {

    Channel channel
    int pos
    double minValue
    double maxValue

    ChannelConfig(Channel channel, int pos, double minValue, double maxValue) {
        this.channel = channel
        this.pos = pos
        this.minValue = minValue
        this.maxValue = maxValue
    }

    Map<String, Boolean> evaluate(AnalyzedData analyzedData) {
        Map analysis = [:]
        analysis["Average delta power"] = (analyzedData.powerBand.averageDeltaPower < maxValue) && (minValue < analyzedData.powerBand.averageDeltaPower)

        return analysis
    }

    static constraints = {
    }
}
