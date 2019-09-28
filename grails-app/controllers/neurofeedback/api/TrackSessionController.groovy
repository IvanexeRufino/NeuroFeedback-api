package neurofeedback.api

class TrackSessionController {

    def treatmentStorageService
    def analysisService

    static allowedMethods = [treatmentSession:'POST']

    def index() {
        render "You should not be doing this"
    }

    def treatmentSession() {
        def dataArray = request.JSON

        def start = new Date().getTime()

        def cb = prepareArraysForChannels(dataArray)
        AnalyzedData analysis = analysisService.getDataAnalyzed(cb.buffer[2])
        treatmentStorageService.storeDataForTreatment(params.id, analysis)

        def end = new Date().getTime()

        println("This just took me " + (end - start))

        if(analysis.powerBand.alphaPower > 1) {
            render "FEEDBACK POSITIVO"
        } else {
            render "FEEDBACK NEGATIVO"
        }
    }

    private static ChannelBuffer prepareArraysForChannels(data) {
        ChannelBuffer cb = new ChannelBuffer()

        data.each { List timeList ->
            cb.addBufferedData(timeList)
        }

        return cb
    }
}
