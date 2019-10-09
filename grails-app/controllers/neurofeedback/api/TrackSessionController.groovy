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
        println("Im about to responde ${response}")


        respond response, formats: ['json']
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

    private static List<AnalyzyedResponse> processResponse(List<AnalyzedData> analyzedDatas, UserTreatment userTreatment) {
        List<AnalyzyedResponse> response = []

        userTreatment.treatment.channelsConfig.each { ChannelConfig channelConfig ->
            def analyzedData = analyzedDatas.find ({ AnalyzedData analyzedData ->
                analyzedData.channelName == channelConfig.channel.name
            })

            AnalyzyedResponse definiteResponse = channelConfig.evaluate(analyzedData)
            AnalyzyedResponse alreadyResponse = response.find({ AnalyzyedResponse analyzedResponse ->
                analyzedResponse.channelName == channelConfig.channel.name
            })

            if(alreadyResponse) {
                if(alreadyResponse.averageBandPower != "Neutral" || alreadyResponse.frequencyBandContribution != "Neutral") {
                    definiteResponse = alreadyResponse
                }
            }

            response.add(definiteResponse)
        }

        return response
    }
}
