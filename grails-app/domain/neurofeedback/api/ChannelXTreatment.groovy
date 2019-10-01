package neurofeedback.api

class ChannelXTreatment {

    Channel channel
    Treatment treatment

    double minValue
    double maxValue
    List buffer

    ChannelXTreatment(Channel channel, Treatment treatment, double minValue, double maxValue) {
        this.channel = channel
        this.treatment = treatment
        this.minValue = minValue
        this.maxValue = maxValue
    }

    static constraints = {
    }
}
