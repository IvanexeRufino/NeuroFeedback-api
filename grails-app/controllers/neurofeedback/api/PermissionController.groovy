package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PermissionController {

    PermissionService permissionService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond permissionService.list(params), model:[permissionCount: permissionService.count()]
    }

    def show(Long id) {
        respond permissionService.get(id)
    }

    def create() {
        respond new Permission(params)
    }

    def save(Permission permission) {
        if (permission == null) {
            notFound()
            return
        }

        try {
            permissionService.save(permission)
        } catch (ValidationException e) {
            respond permission.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'permission.label', default: 'Permission'), permission.id])
                redirect permission
            }
            '*' { respond permission, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond permissionService.get(id)
    }

    def update(Permission permission) {
        if (permission == null) {
            notFound()
            return
        }

        try {
            permissionService.save(permission)
        } catch (ValidationException e) {
            respond permission.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'permission.label', default: 'Permission'), permission.id])
                redirect permission
            }
            '*'{ respond permission, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        permissionService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'permission.label', default: 'Permission'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'permission.label', default: 'Permission'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
