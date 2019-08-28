package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL','ROLE_PATIENT'])
class TratamientoController {

    static Boolean patient = true
    static Boolean professional = true
    static Boolean administrator = true
    def index() {
    	 render(view: "main")
    }
}
