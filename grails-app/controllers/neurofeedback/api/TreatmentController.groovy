package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

@Secured(['ROLE_PROFESSIONAL'])
class TreatmentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static Boolean patient = false
    static Boolean professional = true
    static Boolean administrator = false
    static String friendlyName = "Nuevo tratamiento"

    def index() {
        redirect(action: "create", controller: "UserTreatment")
    }
}
