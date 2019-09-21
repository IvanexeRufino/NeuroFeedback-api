package neurofeedback.api

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import grails.compiler.GrailsCompileStatic
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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

    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    List<Role> getAuthorities() {
        [role]
    }

    List<UserHistory> getTreatments() {
        (UserHistory.findAllByUser(this) as List<UserHistory>)
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
    }

    static mapping = {
	    password column: '`password`'
    }
}
