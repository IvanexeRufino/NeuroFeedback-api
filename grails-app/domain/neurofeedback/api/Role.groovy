package neurofeedback.api

class Role {

    int id
    String name
    String description

    static hasMany = [permissions: Permission]

    static constraints = {
        id (unique: true, maxSize: 11)
        name (unique: true, blank: false, maxSize: 50)
        description (blank: false, maxSize: 255)
    }
}
