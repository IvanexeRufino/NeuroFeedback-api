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
        String userTreatmentId = params.id

        UserTreatment userT = UserTreatment.findById(userTreatmentId)

        //Prepare
        def start = new Date().getTime()
        userT.status = "Live"
        List<AnalyzedData> ads = prepareADSForAnalysis(userT, dataArray)

        //Process
        ads.forEach { analyzedData ->
            analyzedDatas.add(analysisService.getDataAnalyzed(analyzedData))
        }

        //Analyze
        def response = processResponse(analyzedDatas, userT)

        //Storage
        treatmentStorageService.storeDataForTreatment(userTreatmentId, analyzedDatas)

        def end = new Date().getTime()
        println("This just took me " + (end - start))

        render response
    }

    private static List<AnalyzedData> prepareADSForAnalysis(UserTreatment userT, def data) {
        List<List> buffers = []
        List<AnalyzedData> ads = []

        for(int i = 0; i < userT.treatment.channelsConfig.size(); i++) {
            buffers.add([])
        }

        data.each { List timeList ->
            for(int i = 0; i < timeList.size(); i++) {
                buffers[i] += [timeList[i]]
            }
        }

        buffers.each { buffer ->
            ads.add(new AnalyzedData(buffer, userT.frequency))
        }

        return ads
    }

    private static Map processResponse(List<AnalyzedData> analyzedDatas, UserTreatment userTreatment) {
        Map<String, Map<String, Boolean>> response = [:]

        userTreatment.treatment.channelsConfig.eachWithIndex { ChannelConfig channelConfig, int i ->
            response[channelConfig.channel.name] = channelConfig.evaluate(analyzedDatas[i])
            println(analyzedDatas[i].powerBand.getAlphaPowerContribution())
        }

        return response
    }
}
