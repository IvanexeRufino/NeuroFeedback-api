package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN'])
class PermissionXRoleController {

    PermissionXRoleService permissionXRoleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond permissionXRoleService.list(params), model:[permissionXRoleCount: permissionXRoleService.count()]
    }

    def show(Long id) {
        respond permissionXRoleService.get(id)
    }

    def create() {
        respond new PermissionXRole(params)
    }

    def save(PermissionXRole permissionXRole) {
        if (permissionXRole == null) {
            notFound()
            return
        }

        try {
            permissionXRoleService.save(permissionXRole)
        } catch (ValidationException e) {
            respond permissionXRole.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'permissionXRole.label', default: 'PermissionXRole'), permissionXRole.id])
                redirect permissionXRole
            }
            '*' { respond permissionXRole, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond permissionXRoleService.get(id)
    }

    def update(PermissionXRole permissionXRole) {
        if (permissionXRole == null) {
            notFound()
            return
        }

        try {
            permissionXRoleService.save(permissionXRole)
        } catch (ValidationException e) {
            respond permissionXRole.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'permissionXRole.label', default: 'PermissionXRole'), permissionXRole.id])
                redirect permissionXRole
            }
            '*'{ respond permissionXRole, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        permissionXRoleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'permissionXRole.label', default: 'PermissionXRole'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'permissionXRole.label', default: 'PermissionXRole'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
