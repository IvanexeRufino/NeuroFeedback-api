package neurofeedback.api

import grails.gorm.transactions.Transactional

@Transactional
class TreatmentStorageService {

    def treatmentData = [:]

    def storeDataForTreatment(String userTreatmentId, arrayData) {
        arrayData.each { array ->
            array.eachWithIndex{it,index->
                if(!treatmentData[userTreatmentId]) {
                    treatmentData[userTreatmentId] = [:]
                }

                switch(index) {
                    case 0: treatmentData[userTreatmentId] += [ch1: it]
                    case 1: treatmentData[userTreatmentId] += [ch2: it]
                    case 2: treatmentData[userTreatmentId] += [ch3: it]
                    case 3: treatmentData[userTreatmentId] += [ch4: it]
                    case 4: treatmentData[userTreatmentId] += [ch5: it]
                    case 5: treatmentData[userTreatmentId] += [ch6: it]
                    case 6: treatmentData[userTreatmentId] += [ch7: it]
                    case 7: treatmentData[userTreatmentId] += [ch8: it]
                }
            }
        }
    }

    def getDataForTreatment(String userTreatmentId, String channel) {
        treatmentData[userTreatmentId]["ch${channel}"]
    }
}
