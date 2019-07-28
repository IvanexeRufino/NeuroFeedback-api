package neurofeedback.api

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TreatmentParameterSpec extends Specification implements DomainUnitTest<TreatmentParameter> {

    def setup() {
    }

    def cleanup() {
    }

    void "test values in range are valid or not"() {
        when:
            domain.maxValue = 50
            domain.minValue = 30
        then:
            domain.isValueNormal(45)
            !domain.isValueNormal(60)
    }
}
