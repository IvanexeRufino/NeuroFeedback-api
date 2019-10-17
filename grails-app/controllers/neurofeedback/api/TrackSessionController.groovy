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
            ads.add(new AnalyzedData(names[ix], buffer, buffer.size()))
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
    def endTreatment(){
        def userT_id = params.id // user Treatment Id Necesario, chequear que sea del tipo string o int en la linea 92 necesita string.
        List<AnalyzedData> ad = treatmentStorageService.getDataForTreatment(userT_id)
        File file = new File("treatment"+userT_id+".json")
        if(!ad) {
            file.write("[]")
            render "null array"
            return 
        }
        def text = "["
        ad.each {
            text += it.toJson()
            text +=" ,"
        }
        text= text.substring(0, text.length() - 1)
        text+="]"
        file.write(text)
        UserTreatment.executeUpdate("Update UserTreatment u set u.status='Finished' where u.id=:userTId", [userTId: userT_id.toInteger()])
        treatmentStorageService.clearData(userT_id) // Limpia la memoria
        render "ok"
    }

    def treatmentHistory(){
        def userT_id = params.id
        File file = new File("treatment"+userT_id+".json")
        render file.text
    }
}
