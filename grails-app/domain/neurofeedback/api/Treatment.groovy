package neurofeedback.api

class Treatment {

    int id
    String name
    String description
    double duration
    double effectiveness
    Date treatmentTimestamp

    User user

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
        treatmentTimestamp (blank: false)
        duration (blank: false, minValue: 0)
        effectiveness (blank: false, range: 0..100)
    }
}
