package neurofeedback.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class TreatmentParameterServiceSpec extends Specification {

    TreatmentParameterService treatmentParameterService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new TreatmentParameter(...).save(flush: true, failOnError: true)
        //new TreatmentParameter(...).save(flush: true, failOnError: true)
        //TreatmentParameter treatmentParameter = new TreatmentParameter(...).save(flush: true, failOnError: true)
        //new TreatmentParameter(...).save(flush: true, failOnError: true)
        //new TreatmentParameter(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //treatmentParameter.id
    }

    void "test get"() {
        setupData()

        expect:
        treatmentParameterService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<TreatmentParameter> treatmentParameterList = treatmentParameterService.list(max: 2, offset: 2)

        then:
        treatmentParameterList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        treatmentParameterService.count() == 5
    }

    void "test delete"() {
        Long treatmentParameterId = setupData()

        expect:
        treatmentParameterService.count() == 5

        when:
        treatmentParameterService.delete(treatmentParameterId)
        sessionFactory.currentSession.flush()

        then:
        treatmentParameterService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        TreatmentParameter treatmentParameter = new TreatmentParameter()
        treatmentParameterService.save(treatmentParameter)

        then:
        treatmentParameter.id != null
    }
}
