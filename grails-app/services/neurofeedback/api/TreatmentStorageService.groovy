package neurofeedback.api

import grails.gorm.transactions.Transactional

@Transactional
class TreatmentStorageService {

    Map<String, List<AnalyzedData>> treatmentData = [:]

    def storeDataForTreatment(String userTreatmentId, List<AnalyzedData> analysis) {
        treatmentData[userTreatmentId] = analysis
    }

    List<AnalyzedData> getDataForTreatment(String userTreatmentId) {
        treatmentData[userTreatmentId]
    }
}
