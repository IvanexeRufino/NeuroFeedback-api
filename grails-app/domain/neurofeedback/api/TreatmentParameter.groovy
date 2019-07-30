package neurofeedback.api

class TreatmentParameter {

    int id
    double frecuency
    double minValue
    double maxValue

    Treatment treatment
    Stimulus stimulus

    static constraints = {
        id (unique: true, maxSize: 11)
        frecuency (blank: false)
        minValue (blank: false)
        maxValue (blank: false)
    }

    Boolean isValueNormal(double value) {
        return value <= maxValue && value >= minValue
    }
}
