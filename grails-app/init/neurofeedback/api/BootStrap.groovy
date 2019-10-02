package neurofeedback.api

class BootStrap {

    def init = { servletContext ->

		initializeRoles()
		initializeUsers()
		initializeChannels()
		initializeFrequencyBands()
		initializeTreatments()
    }

	static def initializeTreatments() {
		Channel ch3 = Channel.findByName("ch3")
		FrequencyBand fb = FrequencyBand.findByName("DELTA")
		List<ChannelConfig> channelsRelaxation = []

		ChannelConfig cc1 = new ChannelConfig(ch3, fb, 0, 0.25, 1.25, 60, 90)
		cc1.save(flush: true)

		channelsRelaxation.add(cc1)

		Treatment relaxation = new Treatment(
				name: "Relajación",
				description: "Tratamiento para mejorar el manejo de estres de las personas",
				channelsConfig: channelsRelaxation
		)

		relaxation.save(flush: true)

		User patient = User.findByUsername('paciente')
		User particular = User.findByUsername('irufino')

		UserTreatment.create(patient, relaxation, "Pending", 360, 128, 95, true)
		UserTreatment.create(patient, relaxation, "Live", 360, 128, 95, true)
		UserTreatment.create(patient, relaxation, "Finished", 360, 128, 95, true)
		UserTreatment.create(particular, relaxation, "Finished", 360, 128, 95, true)
		UserTreatment.create(particular, relaxation, "Pending", 360, 128, 100, true)
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
		new Channel(name: "ch1", description: "channel 1").save(flush: true)
		new Channel(name: "ch2", description: "channel 2").save(flush: true)
		new Channel(name: "ch3", description: "channel 3").save(flush: true)
		new Channel(name: "t3", description: "channel 1").save(flush: true)
		new Channel(name: "t4", description: "channel 1").save(flush: true)
		new Channel(name: "t4", description: "channel 1").save(flush: true)
		new Channel(name: "t5", description: "channel 1").save(flush: true)
		new Channel(name: "c3", description: "channel 1").save(flush: true)
		new Channel(name: "c4", description: "channel 1").save(flush: true)
		new Channel(name: "cz", description: "channel 1").save(flush: true)
		new Channel(name: "p3", description: "channel 1").save(flush: true)
		new Channel(name: "p4", description: "channel 1").save(flush: true)
		new Channel(name: "pz", description: "channel 1").save(flush: true)
		new Channel(name: "o1", description: "channel 1").save(flush: true)
		new Channel(name: "o2", description: "channel 1").save(flush: true)
		new Channel(name: "oz", description: "channel 1").save(flush: true)
		new Channel(name: "f3", description: "channel 1").save(flush: true)
		new Channel(name: "f4", description: "channel 1").save(flush: true)
		new Channel(name: "f7", description: "channel 1").save(flush: true)
		new Channel(name: "f8", description: "channel 1").save(flush: true)
		new Channel(name: "fz", description: "channel 1").save(flush: true)
		new Channel(name: "fp1", description: "channel 1").save(flush: true)
		new Channel(name: "fp2", description: "channel 1").save(flush: true)
		new Channel(name: "fpz", description: "channel 1").save(flush: true)
	}

	static def initializeFrequencyBands() {
		new FrequencyBand(name: "ALPHA").save(flush: true)
		new FrequencyBand(name: "BETA").save(flush: true)
		new FrequencyBand(name: "DELTA").save(flush: true)
		new FrequencyBand(name: "THETA").save(flush: true)
	}

    def destroy = {
    }
}
