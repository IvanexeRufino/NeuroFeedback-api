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

    static UserTreatment create(User user, Treatment treatment, String status, double duration, int frequency,
                                double minValue, double maxValue, double effectivness,
                                boolean flush = false) {
        def instance = new UserTreatment(user: user, treatment: treatment, status: status, duration: duration,
                frequency: frequency, minValue: minValue, maxValue: maxValue, effectiveness: effectivness,
                treatmentDate: new Date())
        instance.save(flush: flush)
        instance
    }

    def toJson() {
        [treatment: treatment.toJson(), duration: duration, frequency: frequency]
    }
}
