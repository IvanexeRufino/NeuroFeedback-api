package neurofeedback.api

class Treatment_parameter {

    int id
    double frecuency
    double minValue
    double maxValue

    static constraints = {
        id (unique: true, maxSize: 11)
        frecuency (blank: false)
        minValue (blank: false)
        maxValue (blank: false)
    }
}
