package neurofeedback.api
class BootStrap {

    def init = { servletContext ->

    	def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN')
    	def professionalRole = Role.findOrSaveWhere(authority: 'ROLE_PROFESSIONAL')
    	def patientRole = Role.findOrSaveWhere(authority: 'ROLE_PATIENT')
    	
    	def admin = User.findOrSaveWhere(username:'administrador',password:'123456',firstName:'Admin',lastName:'istrador')
    	def professional = User.findOrSaveWhere(username:'profesional',password:'123456',firstName:'Profe',lastName:'ssional')
    	def patient = User.findOrSaveWhere(username:'paciente',password:'123456',firstName:'Pac',lastName:'iente')
    	
    	if(!admin.authorities.contains(adminRole)){
    		UserRole.create(admin,adminRole,true)
    	}
    	if(!professional.authorities.contains(professionalRole)){
    		UserRole.create(professional,professionalRole,true)
    	}
    	if(!patient.authorities.contains(patientRole)){
    		UserRole.create(patient,patientRole,true)
    	}

    }


    def destroy = {
    }
}
