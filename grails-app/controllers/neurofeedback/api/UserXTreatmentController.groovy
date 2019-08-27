package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL'])
class UserXTreatmentController {

    UserXTreatmentService userXTreatmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userXTreatmentService.list(params), model:[userXTreatmentCount: userXTreatmentService.count()]
    }

    def show(Long id) {
        respond userXTreatmentService.get(id)
    }

    def create() {
        respond new UserXTreatment(params)
    }

    def save(UserXTreatment userXTreatment) {
        if (userXTreatment == null) {
            notFound()
            return
        }

        try {
            userXTreatmentService.save(userXTreatment)
        } catch (ValidationException e) {
            respond userXTreatment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userXTreatment.label', default: 'UserXTreatment'), userXTreatment.id])
                redirect userXTreatment
            }
            '*' { respond userXTreatment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userXTreatmentService.get(id)
    }

    def update(UserXTreatment userXTreatment) {
        if (userXTreatment == null) {
            notFound()
            return
        }

        try {
            userXTreatmentService.save(userXTreatment)
        } catch (ValidationException e) {
            respond userXTreatment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userXTreatment.label', default: 'UserXTreatment'), userXTreatment.id])
                redirect userXTreatment
            }
            '*'{ respond userXTreatment, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userXTreatmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userXTreatment.label', default: 'UserXTreatment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userXTreatment.label', default: 'UserXTreatment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
