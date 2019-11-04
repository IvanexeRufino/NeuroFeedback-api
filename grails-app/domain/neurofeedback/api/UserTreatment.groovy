package neurofeedback.api

class UserTreatment {

    int id
    User user
    Treatment treatment

    String status
    int duration

    //Frequency of the EEG device, the client should find the closest 2x exp to send data on that size.
    int frequency

    //Completed after the treatment has ended
    double effectiveness
    Date treatmentDate
    String channel_average

    static constraints = {
        id (unique: true, maxSize: 11)
        frequency (blank: false)
        treatmentDate(nullable: true)
        channel_average(nullable: true)
        duration (nullable: true, minValue: 0)
        effectiveness (blank: true, range: 0..100)
        status(inList: ["Finished", "Pending", "Live"], nullable: true)
    }

    static UserTreatment create(User user, Treatment treatment, String status, double duration, int frequency
                                , boolean flush = false) {
        def instance = new UserTreatment(user: user, treatment: treatment, status: status, duration: duration,
                frequency: frequency)
        instance.save(flush: flush)
        instance
    }

    def toJson() {
        [id: id, treatment: treatment.toJson(), duration: duration, frequency: frequency, effectiveness:effectiveness]
    }
}
