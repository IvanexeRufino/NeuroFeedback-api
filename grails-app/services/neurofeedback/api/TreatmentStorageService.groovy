package neurofeedback.api

import grails.gorm.transactions.Transactional

@Transactional
class TreatmentStorageService {

    def treatmentData = [:]

    def storeDataForTreatment(String userTreatmentId, AnalyzedData analysis) {
        treatmentData[userTreatmentId] = analysis
    }

    AnalyzedData getDataForTreatment(String userTreatmentId, String channel) {
        treatmentData[userTreatmentId]
    }
}
