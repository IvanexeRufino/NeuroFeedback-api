package neurofeedback.api

class User_history {

    int id
    double duration
    double effectiveness

    static constraints = {
        id (unique: true, maxSize: 11)
        duration (unique: true, blank: false, minValue: 0)
        effectiveness (blank: false, range: 0..100)
    }
}
