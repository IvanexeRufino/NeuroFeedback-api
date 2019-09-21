package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_PROFESSIONAL'])
class TreatmentController {

    def springSecurityService
    UserTreatmentService userTreatmentService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static Boolean patient = false
    static Boolean professional = true
    static Boolean administrator = false
    static String friendlyName = "Nuevo tratamiento"

    def index() {
        Role patient = Role.findByAuthority("ROLE_PATIENT")
        List<User> patientUsers = User.findAllByRole(patient)
        respond new UserTreatment(), model:[patientUsers: patientUsers]
    }

    def save(UserTreatment userT) {
        if (userT == null) {
            notFound()
            return
        }

        try {
            User userLoggedIn = springSecurityService.getCurrentUser()

            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    private List<User> getApplicableUsers(Map params) {
        User currentUser = springSecurityService.getCurrentUser()

        if(currentUser.role.authority == "ROLE_PROFESSIONAL") {
            return User.findAllByAssignedDoctor(currentUser) as List<User>
        } else {
            return userService.list(params)
        }

    }
}
