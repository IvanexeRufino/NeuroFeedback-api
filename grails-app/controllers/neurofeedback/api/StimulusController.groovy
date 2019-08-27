package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL'])
class StimulusController {

    StimulusService stimulusService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond stimulusService.list(params), model:[stimulusCount: stimulusService.count()]
    }

    def show(Long id) {
        respond stimulusService.get(id)
    }

    def create() {
        respond new Stimulus(params)
    }

    def save(Stimulus stimulus) {
        if (stimulus == null) {
            notFound()
            return
        }

        try {
            stimulusService.save(stimulus)
        } catch (ValidationException e) {
            respond stimulus.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'stimulus.label', default: 'Stimulus'), stimulus.id])
                redirect stimulus
            }
            '*' { respond stimulus, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond stimulusService.get(id)
    }

    def update(Stimulus stimulus) {
        if (stimulus == null) {
            notFound()
            return
        }

        try {
            stimulusService.save(stimulus)
        } catch (ValidationException e) {
            respond stimulus.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'stimulus.label', default: 'Stimulus'), stimulus.id])
                redirect stimulus
            }
            '*'{ respond stimulus, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        stimulusService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'stimulus.label', default: 'Stimulus'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'stimulus.label', default: 'Stimulus'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
