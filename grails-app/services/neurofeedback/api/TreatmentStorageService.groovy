package neurofeedback.api

import grails.gorm.transactions.Transactional

@Transactional
class TreatmentStorageService {

    def treatmentData = [:]

    def storeDataForTreatment(String userTreatmentId, arrayData) {
        arrayData.each { array ->
            array.eachWithIndex{it,index->
                if(!treatmentData[userTreatmentId]) {
                    treatmentData[userTreatmentId] = []
                }
                if(index == 2 && it != "end") {
                    treatmentData[userTreatmentId] += it
                }

            }
        }
    }

    def getDataForTreatment(String userTreatmentId, String channel) {
        treatmentData[userTreatmentId]
    }
}
