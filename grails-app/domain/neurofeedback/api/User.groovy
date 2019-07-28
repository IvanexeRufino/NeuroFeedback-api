package neurofeedback.api

class User {

    int id

    //Personal data
    String firstName
    String lastName
    String docType
    String docNumber
    Date dateOfBirth

    //Login data
    String email
    String password

    Role role
    static hasMany = [treatments: Treatment]

    static constraints = {
        id (unique: true, maxSize: 11)
        firstName (blank: false, maxSize: 50)
        lastName (blank: false, maxSize: 50)
        dateOfBirth (blank: false)
        docType (blank: false, maxSize: 5)
        docNumber (blank: false, maxSize: 50)
        email (blank:false, email:true, maxSize: 255)
        password (blank: false, maxSize: 50)
    }
}
