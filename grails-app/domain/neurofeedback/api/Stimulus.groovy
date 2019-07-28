package neurofeedback.api

class Stimulus {

    int id
    String name
    String resource

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        resource (blank: false, maxSize: 255)
    }
}
