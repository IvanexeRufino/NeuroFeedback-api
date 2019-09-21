package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_PROFESSIONAL', 'ROLE_PATIENT'])
class UserHistoryController {

    static String friendlyName = "Historial de tratamientos"
    UserHistoryService userHistoryService
    static Boolean patient = true
    static Boolean professional = true
    static Boolean administrator = false
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userHistoryService.list(params), model:[userHistoryCount: userHistoryService.count()]
    }

    def show(Long id) {
        respond userHistoryService.get(id)
    }

    def edit(Long id) {
        respond userHistoryService.get(id)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userHistory.label', default: 'UserHistory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
