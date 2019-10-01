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

        //Prepare
        List<AnalyzedData> ads = prepareADSForAnalysis(userT, dataArray)

        //Process
        ads.forEach { analyzedData ->
            analyzedDatas.add(analysisService.getDataAnalyzed(analyzedData))
        }

        //Storage
        treatmentStorageService.storeDataForTreatment(params.id, analyzedDatas)

        def end = new Date().getTime()

        println("This just took me " + (end - start))

        //Analyze
        if(analyzedDatas[2].powerBand.alphaPower > 1) {
            render "FEEDBACK POSITIVO"
        } else {
            render "FEEDBACK NEGATIVO"
        }
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
}
