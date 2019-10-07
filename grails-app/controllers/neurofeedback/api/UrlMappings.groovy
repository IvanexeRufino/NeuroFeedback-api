package neurofeedback.api

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller:"main")
        "/ping"(controller: PingController)
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/logout"(controller:"Logout")
        "/error"(view:'/error')
        "/mobile/auth"(controller: 'mobile', action: ['POST': 'authMobile', 'GET': 'authMobile'])
        "/mobile/treatments"(controller: 'mobile', action: ['POST': 'getTreatments', 'GET': 'getTreatments'])
        "/mobile/userTreatment"(controller: 'mobile', action: ['POST': 'getUserTreatment', 'GET': 'getUserTreatment'])

    }
}
