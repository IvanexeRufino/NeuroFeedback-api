
// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'neurofeedback.api.User'
grails.plugin.springsecurity.authority.className = 'neurofeedback.api.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               		access: ['permitAll']],
	[pattern: '/ping/**',               access: ['permitAll']],
	[pattern: '/mobile/**',             access: ['permitAll']],
	[pattern: '/trackSession/**',		access: ['permitAll']],
	[pattern: '/treatmentTrack/**',     access: ['permitAll']],
	[pattern: '/error',          		access: ['permitAll']],
	[pattern: '/index',          		access: ['permitAll']],
	[pattern: '/create.gsp',      		access: ['permitAll']],
	[pattern: '/shutdown',       		access: ['permitAll']],
	[pattern: '/assets/**',      		access: ['permitAll']],
	[pattern: '/**/js/**',       		access: ['permitAll']],
	[pattern: '/**/css/**',      		access: ['permitAll']],
	[pattern: '/**/images/**',   		access: ['permitAll']],
	[pattern: '/**/favicon.ico', 		access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      	filters: 'none'],
	[pattern: '/**/js/**',       	filters: 'none'],
	[pattern: '/**/css/**',      	filters: 'none'],
	[pattern: '/**/images/**',   	filters: 'none'],
	[pattern: '/**/favicon.ico', 	filters: 'none'],
	[pattern: '/ping/**', 			filters: 'none'],
	[pattern: '/mobile/**', 		filters: 'none'],
	[pattern: '/trackSession/**', 	filters: 'none'],
	[pattern: '/ping/**', 			filters: 'none'],
	[pattern: '/**',             	filters: 'JOINED_FILTERS']
]
