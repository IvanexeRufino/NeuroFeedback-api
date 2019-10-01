package neurofeedback.api

class ChannelConfig {

    Channel channel
    double minValue
    double maxValue
    List<Double> buffer

    ChannelConfig(Channel channel, double minValue, double maxValue) {
        this.channel = channel
        this.minValue = minValue
        this.maxValue = maxValue
        this.buffer = []
    }

    static constraints = {
    }
}
