package neurofeedback.api

import grails.gorm.transactions.Transactional

@Transactional
class TreatmentStorageService {

    Map<String, List<AnalyzedData>> treatmentData = [:]

    def storeDataForTreatment(String userTreatmentId, List<AnalyzedData> analysis) {
        def storedAnalysis = treatmentData[userTreatmentId]

        if(!storedAnalysis) {
            treatmentData[userTreatmentId] = analysis
        } else {
            storedAnalysis.eachWithIndex { AnalyzedData entry, int i ->
                entry.updateAnalysis(analysis[i])
            }

            treatmentData[userTreatmentId] = storedAnalysis
        }
    }

    List<AnalyzedData> getDataForTreatment(String userTreatmentId) {
        treatmentData[userTreatmentId]
    }

    def clearData(String userTreatmentId){
        treatmentData[userTreatmentId] = null
    }
}
