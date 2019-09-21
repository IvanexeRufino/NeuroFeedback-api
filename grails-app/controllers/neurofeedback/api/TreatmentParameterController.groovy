package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_PROFESSIONAL'])
class TreatmentParameterController {

    TreatmentParameterService treatmentParameterService
    
    static Boolean patient = false
    static Boolean professional = true
    static Boolean administrator = false
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond treatmentParameterService.list(params), model:[treatmentParameterCount: treatmentParameterService.count()]
    }

    def show(Long id) {
        respond treatmentParameterService.get(id)
    }

    def create() {
        respond new TreatmentParameter(params)
    }

    def save(TreatmentParameter treatmentParameter) {
        if (treatmentParameter == null) {
            notFound()
            return
        }

        try {
            treatmentParameterService.save(treatmentParameter)
        } catch (ValidationException e) {
            respond treatmentParameter.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'treatmentParameter.label', default: 'TreatmentParameter'), treatmentParameter.id])
                redirect treatmentParameter
            }
            '*' { respond treatmentParameter, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond treatmentParameterService.get(id)
    }

    def update(TreatmentParameter treatmentParameter) {
        if (treatmentParameter == null) {
            notFound()
            return
        }

        try {
            treatmentParameterService.save(treatmentParameter)
        } catch (ValidationException e) {
            respond treatmentParameter.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'treatmentParameter.label', default: 'TreatmentParameter'), treatmentParameter.id])
                redirect treatmentParameter
            }
            '*'{ respond treatmentParameter, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        treatmentParameterService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'treatmentParameter.label', default: 'TreatmentParameter'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'treatmentParameter.label', default: 'TreatmentParameter'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
