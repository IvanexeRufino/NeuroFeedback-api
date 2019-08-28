package neurofeedback.api

class PingController {

    static Boolean patient = true
    static Boolean professional = true
    static Boolean administrator = true
    def index() {
        render "pong"
    }
}
