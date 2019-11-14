package neurofeedback.api

class BootStrap {

    def init = { servletContext ->

		initializeRoles()
		initializeUsers()
		initializeChannels()
		initializeFrequencyBands()

		initializeRelaxationTreatment()
		initializeMemoryTreatment()
		initializeAttentionTreatment()
		initializeCognitivePerformanceTreatment()

		initializeUserTreatments()
    }

	static def initializeRoles() {
		new Role(authority: 'ROLE_ADMIN', description: 'Usuario administrador de Profesionales',beauty:"Administrador").save(flush: true)
		new Role(authority: 'ROLE_PROFESSIONAL', description: 'Usuario administrador de Pacientes',beauty:"Profesional").save(flush: true)
		new Role(authority: 'ROLE_PATIENT', description: 'Paciente, realiza tratamientos',beauty:"Paciente").save(flush: true)
	}

	static def initializeUsers() {
		User admin = new User(
				username:'administrador',password:'1',firstName:'Administrador',lastName:'Hospital',
				docType: 'DNI',
				docNumber: '11111111',
				dateOfBirth: new Date(),
				email: 'neurocare@gmail.com',
				role: Role.findByAuthority("ROLE_ADMIN")
		).save(flush: true)

		User doctor = new User(
				username:'profesional',password:'1',firstName:'Teresa',lastName:'Tudela',
				docType: 'DNI',
				docNumber: '27439894',
				dateOfBirth: new Date(),
				email: 'teresa.tudela@gmail.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL"),
				assignedDoctor: admin
		)
		doctor.save(flush: true)

		User doctor2 = new User(
				username:'lortigoza',password:'1',firstName:'Lorena',lastName:'Ortigoza',
				docType: 'DNI',
				docNumber: '22323241',
				dateOfBirth: new Date(),
				email: 'lorena.ortigoza@gmail.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL"),
				assignedDoctor: admin
		)
		doctor2.save(flush: true)

		User doctor3 = new User(
				username:'lgalarza',password:'1',firstName:'Laura',lastName:'Galarza',
				docType: 'DNI',
				docNumber: '25892893',
				dateOfBirth: new Date(),
				email: 'laura.galarza@gmail.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL"),
				assignedDoctor: admin
		)
		doctor3.save(flush: true)

		User doctor4 = new User(
				username:'ecasares',password:'1',firstName:'Eduardo',lastName:'Casares',
				docType: 'DNI',
				docNumber: '31432850',
				dateOfBirth: new Date(),
				email: 'eduardo.casares@gmail.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL"),
				assignedDoctor: admin
		)
		doctor4.save(flush: true)


		User doctor5 = new User(
				username:'juceda',password:'1',firstName:'Julian',lastName:'Uceda',
				docType: 'DNI',
				docNumber: '38434698',
				dateOfBirth: new Date(),
				email: 'juli.uceda@gmail.com',
				role: Role.findByAuthority("ROLE_PROFESSIONAL"),
				assignedDoctor: admin
		)
		doctor5.save(flush: true)


		new User(
				username:'paciente',password:'1',firstName:'Jorge',lastName:'Blesa',
				docType: 'DNI',
				docNumber: '1',
				dateOfBirth: new Date(),
				email: 'jblesa@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor
		).save(flush: true)

		new User(
				username:'irufino',password:'1',firstName:'Ivan',lastName:'Rufino',
				docType: 'DNI',
				docNumber: '883938',
				dateOfBirth: new Date(),
				email: 'ivanexerufino@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor
		).save(flush: true)

		new User(
				username:'asosa',password:'1',firstName:'Alberto',lastName:'Sosa',
				docType: 'DNI',
				docNumber: '1357865',
				dateOfBirth: new Date(),
				email: 'alsosa@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor
		).save(flush: true)

		new User(
				username:'ijareño',password:'1',firstName:'Ivan',lastName:'Jareño',
				docType: 'DNI',
				docNumber: '1479238',
				dateOfBirth: new Date(),
				email: 'jareñoivan@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor2
		).save(flush: true)
		new User(
				username:'ccapel',password:'1',firstName:'Claudia',lastName:'Capel',
				docType: 'DNI',
				docNumber: '243982792',
				dateOfBirth: new Date(),
				email: 'ccapel@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor2
		).save(flush: true)

		new User(
				username:'mcuriel',password:'1',firstName:'Maria',lastName:'Curiel',
				docType: 'DNI',
				docNumber: '25546789',
				dateOfBirth: new Date(),
				email: 'mcuriel@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor2
		).save(flush: true)
		new User(
				username:'lcorreas',password:'1',firstName:'Lidia',lastName:'Correas',
				docType: 'DNI',
				docNumber: '275342793',
				dateOfBirth: new Date(),
				email: 'lcorreas@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor2
		).save(flush: true)
	
		new User(
				username:'icampos',password:'1',firstName:'Ines',lastName:'Campos',
				docType: 'DNI',
				docNumber: '10363412',
				dateOfBirth: new Date(),
				email: 'icampos@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor3
		).save(flush: true)

		new User(
				username:'pgallart',password:'1',firstName:'Paula',lastName:'Gallart',
				docType: 'DNI',
				docNumber: '17908923',
				dateOfBirth: new Date(),
				email: 'pgallart@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor3
		).save(flush: true)
		
		new User(
				username:'mgallart',password:'1',firstName:'Miguel',lastName:'Gallart',
				docType: 'DNI',
				docNumber: '17908924',
				dateOfBirth: new Date(),
				email: 'mgallart@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor4
		).save(flush: true)
		new User(
				username:'sroncero',password:'1',firstName:'Salvador',lastName:'Roncero',
				docType: 'DNI',
				docNumber: '17124924',
				dateOfBirth: new Date(),
				email: 'roncero@gmail.com',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor4
		).save(flush: true)
		new User(
				username:'fberrocal',password:'1',firstName:'Francisco',lastName:'Berrocal',
				docType: 'DNI',
				docNumber: '34237987',
				dateOfBirth: new Date(),
				email: 'fberrocal@yahoo.com.ar',
				role: Role.findByAuthority("ROLE_PATIENT"),
				assignedDoctor: doctor5
		).save(flush: true)
	}

	static def initializeChannels() {
		new Channel(name: "ch1", description: "channel 1", pos: 2).save(flush: true)
		new Channel(name: "ch2", description: "channel 2", pos: 1).save(flush: true)
		new Channel(name: "ch3", description: "channel 3", pos: 0).save(flush: true)
		new Channel(name: "t3", description: "Temporal 3", pos: 7).save(flush: true)
		new Channel(name: "t4", description: "Temporal 4", pos: 3).save(flush: true)
		new Channel(name: "t5", description: "Temporal 5", pos: 15).save(flush: true)
		new Channel(name: "t6", description: "Temporal 6", pos: 8).save(flush: true)
		new Channel(name: "c3", description: "Central 3", pos: 6).save(flush: true)
		new Channel(name: "c4", description: "Central 4", pos: 4).save(flush: true)
		new Channel(name: "cz", description: "Central Ground", pos: 5).save(flush: true)
		new Channel(name: "p3", description: "Parietal 3", pos: 13).save(flush: true)
		new Channel(name: "p4", description: "Parietal 4", pos: 9).save(flush: true)
		new Channel(name: "pz", description: "Parietal Ground", pos: 11).save(flush: true)
		new Channel(name: "o1", description: "Occipital 1", pos: 14).save(flush: true)
		new Channel(name: "o2", description: "Occipital 2", pos: 10).save(flush: true)
		new Channel(name: "oz", description: "Occipital Ground", pos: 12).save(flush: true)
		new Channel(name: "f3", description: "Frontal 3", pos: 21).save(flush: true)
		new Channel(name: "f4", description: "Frontal 4", pos: 18).save(flush: true)
		new Channel(name: "f7", description: "Frontal 7", pos: 23).save(flush: true)
		new Channel(name: "f8", description: "Frontal 8", pos: 16).save(flush: true)
		new Channel(name: "fz", description: "Frontal Ground", pos: 19).save(flush: true)
		new Channel(name: "fp1", description: "Pre-Frontal 1", pos: 22).save(flush: true)
		new Channel(name: "fp2", description: "Pre-frontal 2", pos: 17).save(flush: true)
		new Channel(name: "fpz", description: "Pre-Frontal Ground", pos: 20).save(flush: true)
	}

	static def initializeFrequencyBands() {
		new FrequencyBand(name: "ALPHA").save(flush: true)
		new FrequencyBand(name: "BETA").save(flush: true)
		new FrequencyBand(name: "DELTA").save(flush: true)
		new FrequencyBand(name: "THETA").save(flush: true)
	}

	static def initializeRelaxationTreatment() {
		Channel oz = Channel.findByName("oz")
		Channel o1 = Channel.findByName("o1")
		Channel c3 = Channel.findByName("c3")
		FrequencyBand afb = FrequencyBand.findByName("ALPHA")
		List<ChannelConfig> channelsRelaxation = []

		ChannelConfig cc1 = new ChannelConfig(oz, afb, "Positive", 1.5, 4, 25, 75)
		ChannelConfig cc2 = new ChannelConfig(o1, afb, "Positive", 1.5, 4, 25, 75)
		ChannelConfig cc3 = new ChannelConfig(c3, afb, "Positive", 1.5, 4, 15, 50)
		cc1.save(flush: true)
		cc2.save(flush: true)
		cc3.save(flush: true)

		channelsRelaxation.add(cc1)
		channelsRelaxation.add(cc2)
		channelsRelaxation.add(cc3)

		new Treatment(
				name: "Relajacion",
				description: "Tratamiento para mejorar el manejo de estres de las personas.",
				longDescription: "  El sistema procesa las ondas cerebrales y tendrá configurado como rango deseado a aquellas ondas que corresponden a la categoría Alpha (8-13 Hz). Para ello, analizará en tiempo real las muestras recibidas del medidor de ondas cerebrales. ",
				sessionNumber: 7,
				channelsConfig: channelsRelaxation
		).save(flush: true)
	}

	static def initializeMemoryTreatment() {
		Channel cz = Channel.findByName("cz")
		FrequencyBand bfb = FrequencyBand.findByName("BETA")
		FrequencyBand tfb = FrequencyBand.findByName("THETA")
		List<ChannelConfig> channelsMemory= []

		ChannelConfig cc1 = new ChannelConfig(cz, bfb, "Positive", 0.5, 1.25, 35, 75)
		ChannelConfig cc2 = new ChannelConfig(cz, tfb, "Negative", 0.1, 0.3, 30, 50)
		cc1.save(flush: true)
		cc2.save(flush: true)

		channelsMemory.add(cc1)
		channelsMemory.add(cc2)

		new Treatment(
				name: "Memoria",
				description: "Tratamiento para mejorar la memoria de las personas.",
				longDescription: "El sistema procesa las ondas cerebrales y tendrá configurado como rango deseado a aquellas ondas que corresponden a la categoría Lower Alpha (8-10 Hz). Para ello, analizará en tiempo real las muestras recibidas del medidor de ondas cerebrales.",
				sessionNumber: 8,
				channelsConfig: channelsMemory
		).save(flush: true)
	}

	static def initializeAttentionTreatment() {
		Channel cz = Channel.findByName("cz")
		Channel pz = Channel.findByName("pz")
		FrequencyBand bfb = FrequencyBand.findByName("BETA")
		FrequencyBand tfb = FrequencyBand.findByName("THETA")
		List<ChannelConfig> channelsAttention= []

		ChannelConfig cc1 = new ChannelConfig(cz, bfb, "Positive", 0.5, 1.25, 35, 75)
		ChannelConfig cc2 = new ChannelConfig(cz, tfb, "Negative", 0.1, 0.3, 30, 50)

		ChannelConfig cc3 = new ChannelConfig(pz, bfb, "Positive", 0.5, 1.25, 35, 75)
		ChannelConfig cc4 = new ChannelConfig(pz, tfb, "Negative", 0.1, 0.3, 30, 50)

		cc1.save(flush: true)
		cc2.save(flush: true)
		cc3.save(flush: true)
		cc4.save(flush: true)

		channelsAttention.add(cc1)
		channelsAttention.add(cc2)
		channelsAttention.add(cc3)
		channelsAttention.add(cc4)

		new Treatment(
				name: "Concentracion",
				description: "Tratamiento para mejorar la concentración de las personas.",
				longDescription: " El sistema procesa las ondas cerebrales y tendrá configurado como rango deseado a aquellas ondas que corresponden a la categoría Beta (15-20 Hz). Para ello, analizará en tiempo real las muestras recibidas del medidor de ondas cerebrales. ",
				sessionNumber: 20,
				channelsConfig: channelsAttention
		).save(flush: true)
	}

	static def initializeCognitivePerformanceTreatment() {
		Channel p3 = Channel.findByName("p3")
		Channel p4 = Channel.findByName("p4")
		Channel pz = Channel.findByName("pz")
		Channel o1 = Channel.findByName("o1")
		Channel o2 = Channel.findByName("o2")

		FrequencyBand afb = FrequencyBand.findByName("ALPHA")
		List<ChannelConfig> channelsCognitive = []

		ChannelConfig cc1 = new ChannelConfig(p3, afb, "Positive", 1.25, 4, 25, 75)
		ChannelConfig cc2 = new ChannelConfig(pz, afb, "Positive", 1.25, 4, 25, 75)
		ChannelConfig cc3 = new ChannelConfig(p4, afb, "Positive", 1.25, 4, 25, 75)
		ChannelConfig cc4 = new ChannelConfig(o1, afb, "Positive", 1.25, 4, 25, 75)
		ChannelConfig cc5 = new ChannelConfig(o2, afb, "Positive", 1.25, 4, 25, 75)

		cc1.save(flush: true)
		cc2.save(flush: true)
		cc3.save(flush: true)
		cc4.save(flush: true)
		cc5.save(flush: true)

		channelsCognitive.add(cc1)
		channelsCognitive.add(cc2)
		channelsCognitive.add(cc3)
		channelsCognitive.add(cc4)
		channelsCognitive.add(cc5)

		new Treatment(
				name: "Performance Cognitiva",
				description: "Tratamiento para mejorar el funcionamiento del cerebro de las personas.",
				longDescription: "El sistema procesa las ondas cerebrales y tendrá configurado como rango deseado a aquellas ondas que corresponden a la categoría Upper Alpha(10-13 Hz). Para ello, analizará en tiempo real las muestras recibidas del medidor de ondas cerebrales. ",
				sessionNumber: 5,
				channelsConfig: channelsCognitive
		).save(flush: true)
	}

	static def initializeUserTreatments() {
		Treatment relaxation = Treatment.findByName("Relajacion")
		Treatment cognitive = Treatment.findByName("Performance Cognitiva")
		Treatment focus = Treatment.findByName("Concentracion")
		Treatment memory = Treatment.findByName("Memoria")

		User patient = User.findByUsername('paciente')
		User particular = User.findByUsername('irufino')

		UserTreatment.create(patient, relaxation, "Pending", 360, 128, true)
		UserTreatment.create(patient, cognitive, "Pending", 1800, 128, true)
        UserTreatment.create(patient, focus, "Pending", 1200, 128, true)
		UserTreatment.create(patient, relaxation, "Live", 360, 128,true)
		UserTreatment.create(patient, cognitive, "Live", 360, 128, true)
		UserTreatment.create(patient, focus, "Live", 360, 128, true)
		UserTreatment.create(patient, memory, "Live", 360, 128, true)
		UserTreatment.create(patient, relaxation, "Finished", 360, 128, true)

		UserTreatment.create(particular, relaxation, "Finished", 360, 128, true)
		UserTreatment.create(particular, relaxation, "Pending", 360, 128, true)

	}

    def destroy = {
    }
}
