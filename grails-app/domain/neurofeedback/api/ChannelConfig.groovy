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

    static constraints = {
    }
}
