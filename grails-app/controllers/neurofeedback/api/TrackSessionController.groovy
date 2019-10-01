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
        List<AnalyzedData> analyzedDatas = []

        UserTreatment userT = UserTreatment.findById(params.id)
        userT.prepareArraysForChannels(dataArray)

        userT.status = "Live"

        def start = new Date().getTime()

        userT.channelsConfig.forEach { channelConfig ->
            analyzedDatas.add(analysisService.getDataAnalyzed(channelConfig.buffer))
        }
        treatmentStorageService.storeDataForTreatment(params.id, analyzedDatas)

        def end = new Date().getTime()

        println("This just took me " + (end - start))

        if(analyzedDatas[2].powerBand.alphaPower > 1) {
            render "FEEDBACK POSITIVO"
        } else {
            render "FEEDBACK NEGATIVO"
        }
    }
}
