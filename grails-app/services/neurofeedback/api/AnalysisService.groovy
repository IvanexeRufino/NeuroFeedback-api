package neurofeedback.api

import grails.gorm.transactions.Transactional
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType

@Transactional
class AnalysisService {

    def getDataAnalyzed(List data) {

        AnalyzedData analyzedData = new AnalyzedData(data)
        def transformer = new FastFourierTransformer(DftNormalization.STANDARD)
        def dataTransformed = transformer.transform((data as double[]), TransformType.FORWARD)
        def unMirroredData = (dataTransformed as List).take(analyzedData.sampleSize as int)

        unMirroredData.eachWithIndex { Complex entry, int i ->
            analyzedData.addComplex(entry, i)
        }
        return analyzedData
    }
}
