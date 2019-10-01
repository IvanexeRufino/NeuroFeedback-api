package neurofeedback.api

class BootStrap {

    def init = { servletContext ->

		initializeRoles()
		initializeUsers()
		initializeTreatments()
    }

	static def initializeTreatments() {
		List<Channel> channels = initializeChannels()

		Treatment stress = new Treatment(
				name: "Estres",
				description: "Tratamiento para mejorar el manejo de estres de las personas",
				channelsConfig: channels
		)
		Treatment concentration = new Treatment(
				name: "Concentración",
				description: "Tratamiento para mejorar la atención y los tiempos de concentración de las personas",
				channelsConfig: channels
		)

		stress.save(flush: true)
		concentration.save(flush: true)

		User patient = User.findByUsername('paciente')
		User particular = User.findByUsername('irufino')

		UserTreatment.create(patient, stress, "Finished", 36000, 128,12, 13, 95, true)
		UserTreatment.create(patient, concentration, "Pending", 36000, 128,12, 13, 86, true)
		UserTreatment.create(particular, stress, "Finished", 360, 128,12, 13, 100, true)
		UserTreatment.create(particular, stress, "Live", 360, 128,12, 13, 100, true)
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

		User doctor = new User(
				username:'profesional',password:'1',firstName:'Profe',lastName:'ssional',
				docType: 'DNI',
				docNumber: '22222222',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL")
		)
		doctor.save(flush: true)

		User doctor2 = new User(
				username:'profe',password:'1',firstName:'Profe',lastName:'ssional',
				docType: 'DNI',
				docNumber: '22222222',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL")
		)
		doctor2.save(flush: true)

		new User(
				username:'paciente',password:'1',firstName:'Pac',lastName:'iente',
				docType: 'DNI',
				docNumber: '33333333',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor
		).save(flush: true)

		new User(
				username:'irufino',password:'1',firstName:'ivan',lastName:'rufino',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'smth@smth.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor2
		).save(flush: true)
	}

	static def initializeChannels() {
		def channels = []

		def ch1 = new Channel(name: "ch1")
		def ch2 = new Channel(name: "ch2")
		def ch3 = new Channel(name: "ch3")
		def ch4 = new Channel(name: "ch4")
		def ch5 = new Channel(name: "ch5")
		def ch6 = new Channel(name: "ch6")
		def ch7 = new Channel(name: "ch7")
		def ch8 = new Channel(name: "ch8")

		ch1.save(flush:true)
		ch2.save(flush:true)
		ch3.save(flush:true)
		ch4.save(flush:true)
		ch5.save(flush:true)
		ch6.save(flush:true)
		ch7.save(flush:true)
		ch8.save(flush:true)

		def cc1 = new ChannelConfig(ch1, 0, 0, 1)
		def cc2 = new ChannelConfig(ch2, 1, 0, 1)
		def cc3 = new ChannelConfig(ch3, 2, 0, 1)
		def cc4 = new ChannelConfig(ch4, 3, 0, 1)
		def cc5 = new ChannelConfig(ch5, 4, 0, 1)
		def cc6 = new ChannelConfig(ch6, 5, 0, 1)
		def cc7 = new ChannelConfig(ch7, 6, 0, 1)
		def cc8 = new ChannelConfig(ch8, 7, 0, 1)

		cc1.save(flush: true)
		cc2.save(flush: true)
		cc3.save(flush: true)
		cc4.save(flush: true)
		cc5.save(flush: true)
		cc6.save(flush: true)
		cc7.save(flush: true)
		cc8.save(flush: true)

		channels.add(cc1)
		channels.add(cc2)
		channels.add(cc3)
		channels.add(cc4)
		channels.add(cc5)
		channels.add(cc6)
		channels.add(cc7)
		channels.add(cc8)

		return channels
	}

    def destroy = {
    }
}
