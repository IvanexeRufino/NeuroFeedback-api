package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_PROFESSIONAL'])
class LiveTreatmentController {

    static Boolean patient = false
    static Boolean professional = true
    static Boolean administrator = false
    def index() {
    	 render(view: "main")
    }
}
