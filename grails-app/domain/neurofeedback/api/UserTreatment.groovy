package neurofeedback.api

class UserTreatment {

    int id
    User user
    Treatment treatment

    String status
    double duration

    //Frequency of the EEG device, the client should find the closest 2x exp to send data on that size.
    int frequency

    //Completed after the treatment has ended
    double effectiveness
    Date treatmentDate

    static constraints = {
        id (unique: true, maxSize: 11)
        frequency (blank: false)
        treatmentDate(nullable: true)
        duration (nullable: true, minValue: 0)
        effectiveness (blank: true, range: 0..100)
        status(inList: ["Finished", "Pending", "Live"], nullable: true)
    }

    static UserTreatment create(User user, Treatment treatment, String status, double duration, int frequency
                                , double effectivness, boolean flush = false) {
        def instance = new UserTreatment(user: user, treatment: treatment, status: status, duration: duration,
                frequency: frequency, effectiveness: effectivness)
        instance.save(flush: flush)
        instance
    }

    def toJson() {
        [id: id, treatment: treatment.toJson(), duration: duration, frequency: frequency]
    }
}
