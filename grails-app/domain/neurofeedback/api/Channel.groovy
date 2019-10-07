package neurofeedback.api

class Channel {

    int id
    String name
    String description
    int pos

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
    }
}
