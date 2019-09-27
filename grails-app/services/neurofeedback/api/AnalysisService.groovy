package neurofeedback.api

import grails.gorm.transactions.Transactional
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType

@Transactional
class AnalysisService {

    def iteration = 1
    def sampleSize = 128

    def getDataAnalyzed(data) {
        def sampledData = data.collate(sampleSize)[iteration]

        def transformer = new FastFourierTransformer(DftNormalization.STANDARD)
        def dataTransformed = transformer.transform((sampledData as double[]), TransformType.FORWARD)
        def mirroredData = (dataTransformed as List).take((sampleSize/2) as int)
        AnalyzedData analyzedData = new AnalyzedData()

        mirroredData.eachWithIndex { Complex entry, int i ->
            analyzedData.addComplex(entry, i, sampleSize/2)
        }
        println("holaa22a")
        iteration++

        return analyzedData
    }
}
