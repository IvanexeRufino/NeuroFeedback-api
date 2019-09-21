package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL','ROLE_PATIENT'])
class MainController {

    def index() {
        render(view: "/index")
    }
}
