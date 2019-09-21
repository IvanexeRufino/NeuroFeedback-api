package neurofeedback.api

class Treatment {

    int id
    String name
    String description

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
    }

    String toString() {
        return name
    }
}
