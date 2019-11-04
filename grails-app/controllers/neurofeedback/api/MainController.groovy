package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL','ROLE_PATIENT'])
class MainController {

    def springSecurityService
    def index() {
		User userLoggedIn = springSecurityService.getCurrentUser()
		def tratamientos_pendientes = UserTreatment.countByUserAndStatus(userLoggedIn,"Pending")
		def tratamientos_finalizados = UserTreatment.countByUserAndStatus(userLoggedIn,"Finished")
		
		Date now = new Date()
		now.clearTime()
		
		def tratamientos_hoy = UserTreatment.countByTreatmentDateBetween(now,now+1)		
		def personas_a_cargo = User.countByAssignedDoctor(userLoggedIn)
        render(view: "/index.gsp", model:[usuario:userLoggedIn,
        	tratamientos_hoy:tratamientos_hoy,tratamientos_pendientes:tratamientos_pendientes,
        	tratamientos_finalizados:tratamientos_finalizados,personas_a_cargo:personas_a_cargo])
    }
}
