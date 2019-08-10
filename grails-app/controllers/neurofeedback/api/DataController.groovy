package neurofeedback.api

class DataController {

    def index() {
        def smth = request.JSON

        print smth

        render "pong"
    }
}
