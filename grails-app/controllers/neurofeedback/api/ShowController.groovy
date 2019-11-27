package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

@Secured(['ROLE_PROFESSIONAL', 'ROLE_ADMIN'])
class ShowController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    static Boolean patient = false
    static Boolean professional = true
    static Boolean administrator = true
    static String friendlyName = "Tratamientos Disponibles"
    static String adminFriendlyName = "Tratamientos Disponibles"

    def index() {
    	List<Treatment> treatments = Treatment.findAll() 
        render(view: "index.gsp", model:[treatments: treatments])
    }
}
