package neurofeedback.api

import java.util.stream.Collectors

class UserTreatment {

    int id
    User user
    Treatment treatment

    String status
    double duration

    //Frecuency of the EEG device, the client should find the closest 2x exp to send data on that size.
    int frecuency
    List<ChannelConfig> channelsConfig

    //Completed after the treatment has ended
    double effectiveness
    Date treatmentDate

    static constraints = {
        id (unique: true, maxSize: 11)
        frecuency (blank: false)
        treatmentDate(nullable: true)
        duration (nullable: true, minValue: 0)
        effectiveness (blank: true, range: 0..100)
        status(inList: ["Finished", "Pending", "Live"], nullable: true)
    }

    static UserTreatment create(User user, Treatment treatment, String status, double duration, int frecuency,
                                double minValue, double maxValue, double effectivness, List<ChannelConfig> configs,
                                boolean flush = false) {
        def instance = new UserTreatment(user: user, treatment: treatment, status: status, duration: duration,
                frecuency: frecuency, minValue: minValue, maxValue: maxValue, effectiveness: effectivness,
                channelsConfig: configs, treatmentDate: new Date())
        instance.save(flush: flush)
        instance
    }

    def toJson() {
        [treatment: treatment.toJson(),duration: duration,frecuency: frecuency, channels: channelsConfig]
    }

    void prepareArraysForChannels(List<List<Double>> data) {
        data.each{ List<Double> timeList ->
            timeList.eachWithIndex { def entry, int ix ->
                channelsConfig[ix].buffer += [entry as Double]
            }
        }
    }

}
