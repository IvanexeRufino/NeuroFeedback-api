package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL'])
class UserHistoryController {

    UserHistoryService userHistoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userHistoryService.list(params), model:[userHistoryCount: userHistoryService.count()]
    }

    def show(Long id) {
        respond userHistoryService.get(id)
    }

    def create() {
        respond new UserHistory(params)
    }

    def save(UserHistory userHistory) {
        if (userHistory == null) {
            notFound()
            return
        }

        try {
            userHistoryService.save(userHistory)
        } catch (ValidationException e) {
            respond userHistory.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userHistory.label', default: 'UserHistory'), userHistory.id])
                redirect userHistory
            }
            '*' { respond userHistory, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userHistoryService.get(id)
    }

    def update(UserHistory userHistory) {
        if (userHistory == null) {
            notFound()
            return
        }

        try {
            userHistoryService.save(userHistory)
        } catch (ValidationException e) {
            respond userHistory.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userHistory.label', default: 'UserHistory'), userHistory.id])
                redirect userHistory
            }
            '*'{ respond userHistory, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userHistoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userHistory.label', default: 'UserHistory'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
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
