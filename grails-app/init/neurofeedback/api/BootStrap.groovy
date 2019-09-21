package neurofeedback.api

class BootStrap {

    def init = { servletContext ->

		initializeRoles()
		initializeUsers()
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
		User particular = User.findByUsername('irufino')

		UserTreatment.create(patient, stress,36000, 95, true,true)
		UserTreatment.create(patient, concentration,36000, 86, false, true)

		UserTreatment.create(particular, concentration,360, 100, true, true)
	}

	static def initializeRoles() {
		new Role(authority: 'ROLE_ADMIN', description: 'Role that can administrate doctors').save(flush: true)
		new Role(authority: 'ROLE_PROFESSIONAL', description: 'Role that can create treatments and users').save(flush: true)
		new Role(authority: 'ROLE_PATIENT', description: 'Role that can take treatments').save(flush: true)
	}

	static def initializeUsers() {
		new User(
				username:'administrador',password:'1',firstName:'Admin',lastName:'istrador',
				docType: 'DNI',
				docNumber: '11111111',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_ADMIN")
		).save(flush: true)

		new User(
				username:'profesional',password:'1',firstName:'Profe',lastName:'ssional',
				docType: 'DNI',
				docNumber: '22222222',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL")
		).save(flush: true)

		new User(
				username:'paciente',password:'1',firstName:'Pac',lastName:'iente',
				docType: 'DNI',
				docNumber: '33333333',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_PATIENT")
		).save(flush: true)

		new User(
				username:'irufino',password:'1',firstName:'ivan',lastName:'rufino',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_PATIENT")
		).save(flush: true)
	}

    def destroy = {
    }
}
