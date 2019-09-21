package neurofeedback.api

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_PROFESSIONAL', 'ROLE_PATIENT'])
class UserTreatmentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static Boolean patient = true
    static Boolean professional = true
    static Boolean administrator = false

    static String friendlyName = "Historial de tratamientos"
    UserTreatmentService userTreatmentService
    def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List history = getApplicableHistory()

        respond history, model:[userTreatmentCount: history.size()]
    }

    def show(Long id) {
        respond userTreatmentService.get(id)
    }

    def edit(Long id) {
        respond userTreatmentService.get(id)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userTreatment.label', default: 'UserTreatment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private List getApplicableHistory() {
        User user = springSecurityService.getCurrentUser()

        if(user.role.authority == "ROLE_PROFESSIONAL") {

        }

        return user.treatments
    }
}
