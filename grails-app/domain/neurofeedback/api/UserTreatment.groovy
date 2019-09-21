package neurofeedback.api

class UserTreatment {

    User user
    Treatment treatment
    Date treatmentDate
    double duration
    double effectiveness
    boolean finished

    static constraints = {
        treatmentDate (blank: false)
        duration (blank: false, minValue: 0)
        effectiveness (blank: false, range: 0..100)
    }

    static UserTreatment create(User user, Treatment treatment, double duration, double effectiveness,
                                boolean finished, boolean flush = false) {
        def instance = new UserTreatment(user: user, treatment: treatment, duration: duration,
                effectiveness: effectiveness, treatmentDate: new Date(), finished: finished)

        instance.save(flush: flush)
        instance
    }

}
