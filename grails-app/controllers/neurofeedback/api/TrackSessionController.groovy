package neurofeedback.api

class TrackSessionController {

    def treatmentStorageService

    static allowedMethods = [treatmentSession:'POST']

    def index() {
        render "You should not be doing this"
    }

    def treatmentSession() {
        def dataArray = request.JSON
        treatmentStorageService.storeDataForTreatment(params.id, dataArray)

        render "pong"
    }
}
