package neurofeedback.api

class BootStrap {

    def init = { servletContext ->

		initializeUsersAndRoles()
		initializeTreatments()
    }

	static def initializeTreatments() {
		Treatment stress = Treatment.findOrSaveWhere(
				name: "Estres",
				description: "Tratamiento para mejorar el manejo de estres de las personas"
		)

		Treatment concentration = Treatment.findOrSaveWhere(
				name: "Concentración",
				description: "Tratamiento para mejorar la atención y los tiempos de concentración de las personas"
		)

		User patient = User.findByUsername('paciente')

		UserHistory.create(patient, stress,36000, 95, true)
		UserHistory.create(patient, concentration,36000, 86, true)
	}

	static def initializeUsersAndRoles() {
		Role adminRole = Role.findOrSaveWhere(authority: 'ROLE_ADMIN', description: 'Role that can administrate doctors')
		Role professionalRole = Role.findOrSaveWhere(authority: 'ROLE_PROFESSIONAL',
				description: 'Role that can create treatments and users')
		Role patientRole = Role.findOrSaveWhere(authority: 'ROLE_PATIENT', description: 'Role that can take treatments')

		User admin = User.findOrSaveWhere(
				username:'administrador',password:'123456',firstName:'Admin',lastName:'istrador',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'smth@smth.com'
		)
		User professional = User.findOrSaveWhere(
				username:'profesional',password:'123456',firstName:'Profe',lastName:'ssional',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'smth@smth.com'
		)
		User patient = User.findOrSaveWhere(
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
