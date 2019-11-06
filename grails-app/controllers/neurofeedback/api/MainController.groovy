package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL','ROLE_PATIENT'])
class MainController {

    def springSecurityService
    def index() {
		User userLoggedIn = springSecurityService.getCurrentUser()
		Date now = new Date()
		now.clearTime()
		switch(userLoggedIn.role.authority) {
			case "ROLE_ADMIN":
				def tratamientos_hoy = UserTreatment.countByTreatmentDateBetween(now,now+1)	
				def usuarios_registrados = User.count()
				def usuarios = User.findAll()
        		render(view: "/index.gsp", model:[usuario:userLoggedIn,
        			tratamientos_hoy:tratamientos_hoy,usuarios_registrados:usuarios_registrados,usuarios:usuarios])
			break
			case "ROLE_PROFESSIONAL":
				def tratamientos_hoy = UserTreatment.countByTreatmentDateBetween(now,now+1)	
				def personas_a_cargo = User.countByAssignedDoctor(userLoggedIn)	
				def usuarios = User.findAllByAssignedDoctor(userLoggedIn)
				render(view: "/index.gsp", model:[usuario:userLoggedIn,tratamientos_hoy:tratamientos_hoy,personas_a_cargo:personas_a_cargo,,usuarios:usuarios])
			break
			case "ROLE_PATIENT":
				def tratamientos_pendientes = UserTreatment.countByUserAndStatus(userLoggedIn,"Pending")
				def tratamientos_finalizados = UserTreatment.countByUserAndStatus(userLoggedIn,"Finished")
				def tratamientos = UserTreatment.findAllByUser(userLoggedIn)
				render(view: "/index.gsp", model:[usuario:userLoggedIn,tratamientos:tratamientos,tratamientos_pendientes:tratamientos_pendientes,tratamientos_finalizados:tratamientos_finalizados])
			break
		}
		
    }
}
