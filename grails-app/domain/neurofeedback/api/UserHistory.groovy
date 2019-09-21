package neurofeedback.api

class UserHistory {

    User user
    Treatment treatment
    Date treatmentDate
    double duration
    double effectiveness

    static constraints = {
        treatmentDate (blank: false)
        duration (blank: false, minValue: 0)
        effectiveness (blank: false, range: 0..100)
    }

    static UserHistory create(User user, Treatment treatment, double duration, double effectiveness,
                              boolean flush = false) {
        def instance = new UserHistory(user: user, treatment: treatment, duration: duration,
                effectiveness: effectiveness, treatmentDate: new Date())

        instance.save(flush: flush)
        instance
    }

}
