package neurofeedback.api

class TrackSessionController {

    static allowedMethods = [treatmentSession:'POST']

    def index() {
        render "You should not be doing this"
    }

    def treatmentSession() {
        def smth = request.JSON

        print smth
        print params.id

        render "pong"
    }
}
