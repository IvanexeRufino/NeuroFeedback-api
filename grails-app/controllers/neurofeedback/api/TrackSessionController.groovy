package neurofeedback.api

import java.util.stream.Collectors

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
        List<AnalyzedData> ads = []
        Set<String> names = userT.treatment.getChannels()

        data.eachWithIndex { buffer, ix ->
            ads.add(new AnalyzedData(names[ix], buffer, buffer.size()))
        }

        return ads
    }

    private List<AnalyzyedResponse> processResponse(List<AnalyzedData> analyzedDatas, UserTreatment userTreatment) {
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
            analyzedData.feedback = definiteResponse
        }

        return response
    }

    def cancelTreatment() {
        def userT_id = params.id
        println("Canceling the treatment")

        UserTreatment.executeUpdate("Update UserTreatment u set u.status='Pending' where u.id=:userTId", [userTId: userT_id.toInteger()])
        treatmentStorageService.clearData(userT_id)

        def responseMap = ["status": "200", "message": "ok"]
        respond responseMap, formats: ['json']

    }

    def endTreatment(){
        def userT_id = params.id
        println("Ending the treatment")

        createGraphFile(userT_id)
        calculateEffectiveness(userT_id)
        calculateAverage(userT_id)
        UserTreatment.executeUpdate("Update UserTreatment u set u.status='Finished' where u.id=:userTId", [userTId: userT_id.toInteger()])
        UserTreatment.executeUpdate("Update UserTreatment u set u.treatmentDate=:date where u.id=:userTId", [date: new Date(), userTId: userT_id.toInteger()])
        treatmentStorageService.clearData(userT_id) 
        def responseMap = ["status": "200", "message": "ok"]
        respond responseMap, formats: ['json']
    }

    def treatmentHistory(){
        def userT_id = params.id
        File file = new File("treatment"+userT_id+".json")
        render file.text
    }

    def createGraphFile(String userT_id) {

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
    }

    def calculateEffectiveness(String userT_id) {
        double calculatedEffectiveness = 0
        List<AnalyzedData> ads = treatmentStorageService.getDataForTreatment(userT_id)
        List<AnalyzyedResponse> ars = ads.stream().map { a ->
            a.feedback
        }.collect(Collectors.toList())

        for(ar in ars) {
            switch (ar.frequencyBandContribution) {
                case "Positive":
                    calculatedEffectiveness += 2
                    break
                case "Neutral":
                    calculatedEffectiveness += 1
                    break
                default:
                    break
            }

            switch (ar.averageBandPower) {
                case "Positive":
                    calculatedEffectiveness += 2
                    break
                case "Neutral":
                    calculatedEffectiveness += 1
                    break
                default:
                    break
            }
        }

        def random = -5 + (Math.random().toDouble() * 10)

        calculatedEffectiveness = ((calculatedEffectiveness/(ars.size() * 2 * 2)) * 100 ) + random

        UserTreatment.executeUpdate("Update UserTreatment u set u.effectiveness=:calculatedEffectiveness where u.id=:userTId", [calculatedEffectiveness: calculatedEffectiveness, userTId: userT_id.toInteger()])
    }
    def calculateAverage(String userT_id){
        List<AnalyzedData> ads = treatmentStorageService.getDataForTreatment(userT_id)
        def channel_average = ""
        List<AnalyzyedResponse> ars = ads.stream().map { a ->
            channel_average+=a.powerBand.getAverageBandPower().toString()+","
        }.collect(Collectors.toList())

        channel_average = channel_average.substring(0, channel_average.length() - 1)
        channel_average += ""

        UserTreatment.executeUpdate("Update UserTreatment u set u.channel_average=:channel_average where u.id=:userTId", [channel_average: channel_average, userTId: userT_id.toInteger()])
    }
}
