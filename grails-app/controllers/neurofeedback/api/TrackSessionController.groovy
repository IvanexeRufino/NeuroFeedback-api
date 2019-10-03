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
        Set<String> names = userT.treatment.getChannels()

        for(int i = 0; i < names.size(); i++) {
            buffers.add([])
        }

        data.each { List timeList ->
            for(int i = 0; i < timeList.size(); i++) {
                buffers[i] += [timeList[i]]
            }
        }

        buffers.eachWithIndex { buffer, ix ->
            ads.add(new AnalyzedData(names[ix], buffer, userT.frequency))
        }

        return ads
    }

    private static Map processResponse(List<AnalyzedData> analyzedDatas, UserTreatment userTreatment) {
        Map<String, Map<String, String>> response = [:]

        userTreatment.treatment.channelsConfig.each { ChannelConfig channelConfig ->
            def analyzedData = analyzedDatas.find ({ AnalyzedData analyzedData ->
                analyzedData.channelName == channelConfig.channel.name
            })

            Map<String, String> definiteResponse = channelConfig.evaluate(analyzedData)
            Map<String, String> alreadyResponse = response[channelConfig.channel.name]

            if(alreadyResponse) {
                if(alreadyResponse["Total power"] != "Neutral" || alreadyResponse["Average band power"] != "Neutral") {
                    definiteResponse = alreadyResponse
                }
            }

            response[channelConfig.channel.name] = definiteResponse
        }

        return response
    }
}
