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

        userT.status = "Live"

        def start = new Date().getTime()

        def cb = prepareArraysForChannels(dataArray)
        cb.buffer.forEach { buffer ->
            analyzedDatas.add(analysisService.getDataAnalyzed(buffer, userT.frequency))
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

    private static ChannelBuffer prepareArraysForChannels(data) {
        ChannelBuffer cb = new ChannelBuffer()

        data.each { List timeList ->
            cb.addBufferedData(timeList)
        }

        return cb
    }
}
