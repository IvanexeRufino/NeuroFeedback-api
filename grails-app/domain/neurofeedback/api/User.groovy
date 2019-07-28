package neurofeedback.api

class User {

    int id
    String firstName
    String lastName
    String docType
    String docNumber
    String email
    Date dateOfBirth
    Role role

    static constraints = {
        id (unique: true, maxSize: 11)
        firstName (blank: false, maxSize: 50)
        lastName (blank: false, maxSize: 50)
        dateOfBirth (blank: false)
        docType (blank: false, maxSize: 5)
        docNumber (blank: false, maxSize: 50)
        email (blank:false, email:true, maxSize: 255)
    }
}
