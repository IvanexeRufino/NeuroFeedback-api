package neurofeedback.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class StimulusServiceSpec extends Specification {

    StimulusService stimulusService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Stimulus(...).save(flush: true, failOnError: true)
        //new Stimulus(...).save(flush: true, failOnError: true)
        //Stimulus stimulus = new Stimulus(...).save(flush: true, failOnError: true)
        //new Stimulus(...).save(flush: true, failOnError: true)
        //new Stimulus(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //stimulus.id
    }

    void "test get"() {
        setupData()

        expect:
        stimulusService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Stimulus> stimulusList = stimulusService.list(max: 2, offset: 2)

        then:
        stimulusList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        stimulusService.count() == 5
    }

    void "test delete"() {
        Long stimulusId = setupData()

        expect:
        stimulusService.count() == 5

        when:
        stimulusService.delete(stimulusId)
        sessionFactory.currentSession.flush()

        then:
        stimulusService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Stimulus stimulus = new Stimulus()
        stimulusService.save(stimulus)

        then:
        stimulus.id != null
    }
}
