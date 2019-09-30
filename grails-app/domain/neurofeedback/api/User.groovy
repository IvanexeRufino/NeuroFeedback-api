package neurofeedback.api

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

    private static final long serialVersionUID = 1

    int id
    String firstName
    String lastName
    String docType
    String docNumber
    Date dateOfBirth
    String email
    String username
    String password
    Role role

    private boolean enabled = true
    private boolean accountExpired
    private boolean accountLocked
    private boolean passwordExpired
    User assignedDoctor

    List<Role> getAuthorities() {
        [role]
    }

    List<UserTreatment> getTreatments() {
        (UserTreatment.findAllByUser(this) as List<UserTreatment>)
    }

    static constraints = {
        id (unique: true, maxSize: 11)
        firstName (blank: false, maxSize: 50)
        lastName (blank: false, maxSize: 50)
        dateOfBirth (blank: false)
        docType (blank: false, maxSize: 5)
        docNumber (blank: false, maxSize: 50)
        email (blank:false, email:true, maxSize: 255)
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: true
        assignedDoctor nullable: true, blank: true
    }

    static mapping = {
	    password column: '`password`'
    }

    def toJson(){
        ["userName": username, "firstName": firstName, "lastName": lastName, "email": email]
    }
}
