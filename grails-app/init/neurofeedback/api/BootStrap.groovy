package neurofeedback.api
class BootStrap {

    def init = { servletContext ->

    	def adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN', description: 'Role that can administrate doctors')
    	def professionalRole = Role.findOrSaveWhere(authority: 'ROLE_PROFESSIONAL',
				description: 'Role that can create treatments and users')
    	def patientRole = Role.findOrSaveWhere(authority: 'ROLE_PATIENT', description: 'Role that can take treatments')
    	
    	def admin = User.findOrSaveWhere(
				username:'administrador',password:'123456',firstName:'Admin',lastName:'istrador',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'smth@smth.com'
		)
    	def professional = User.findOrSaveWhere(
				username:'profesional',password:'123456',firstName:'Profe',lastName:'ssional',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'smth@smth.com'
		)
    	def patient = User.findOrSaveWhere(
				username:'paciente',password:'123456',firstName:'Pac',lastName:'iente',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'smth@smth.com'
		)
    	
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
