package neurofeedback.api

class Treatment {

    int id
    String name
    String description

    User user
    static hasMany = [treatmentParameters: TreatmentParameter]

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
    }
}
