package neurofeedback.api

class UserHistory {

    int id
    double duration
    double effectiveness

    User user
    static hastMany = [treatments: Treatment]

    static constraints = {
        id (unique: true, maxSize: 11)
        duration (unique: true, blank: false, minValue: 0)
        effectiveness (blank: false, range: 0..100)
    }
}
