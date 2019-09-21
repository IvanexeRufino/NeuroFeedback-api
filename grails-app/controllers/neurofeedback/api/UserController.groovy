package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL'])
class UserController {

    UserService userService
    def springSecurityService

    static Boolean patient = false
    static Boolean professional = true
    static Boolean administrator = true
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static String friendlyName = "Usuarios del sistema"

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        List<User> applicableUsers = getApplicableUsers(params)
        respond applicableUsers, model:[userCount: applicableUsers.size()]
    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
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

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
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
