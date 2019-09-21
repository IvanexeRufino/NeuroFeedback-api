package neurofeedback.api

class UserTreatment {

    User user
    Treatment treatment

    String status
    double duration
    double frecuency
    double minValue
    double maxValue

    //Completed after the treatment has ended
    double effectiveness
    Date treatmentDate

    static constraints = {
        id (unique: true, maxSize: 11)
        frecuency (blank: false)
        minValue (blank: false)
        maxValue (blank: false)
        treatmentDate (blank: false)
        duration (blank: false, minValue: 0)
        effectiveness (blank: false, range: 0..100)
        status(inList: ["Finished", "Pending", "Live"], blank: false)
    }

    static UserTreatment create(User user, Treatment treatment, String status, double duration, double frecuency,
                                double minValue, double maxValue, double effectivness, boolean flush = false) {
        def instance = new UserTreatment(user: user, treatment: treatment, status: status, duration: duration,
                frecuency: frecuency, minValue: minValue, maxValue: maxValue, effectiveness: effectivness,
                treatmentDate: new Date())

        instance.save(flush: flush)
        instance
    }

    Boolean isValueNormal(double value) {
        return value <= maxValue && value >= minValue
    }

    def toJson() {
        [treatment: treatment.toJson(),duration: duration,frecuency: frecuency, minValue: minValue, maxValue: maxValue]
    }

}
