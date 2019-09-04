package neurofeedback.api

class DataController {

    def index() {
        render "You should not be doing this"
    }

    def treatmentSession() {
        def smth = request.JSON

        print smth

        render "pong"
    }
}
