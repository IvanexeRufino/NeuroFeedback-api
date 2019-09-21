package neurofeedback.api

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/ping"(controller: PingController)
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/logout"(controller:"Logout")
        "/error"(view:'/error')
        "/mobile/auth"(controller: 'mobile', action: ['POST': 'authMobile', 'GET': 'authMobile'])
        "/mobile/treatments"(controller: 'mobile', action: ['POST': 'getTreatment', 'GET': 'getTreatment'])
        "/treatmentTrack"(controller: 'data', action: ['POST': 'treatmentSession', 'GET': 'index'])
        
    }
}
