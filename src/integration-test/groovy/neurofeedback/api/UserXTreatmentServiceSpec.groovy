package neurofeedback.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class UserXTreatmentServiceSpec extends Specification {

    UserXTreatmentService userXTreatmentService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new UserXTreatment(...).save(flush: true, failOnError: true)
        //new UserXTreatment(...).save(flush: true, failOnError: true)
        //UserXTreatment userXTreatment = new UserXTreatment(...).save(flush: true, failOnError: true)
        //new UserXTreatment(...).save(flush: true, failOnError: true)
        //new UserXTreatment(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //userXTreatment.id
    }

    void "test get"() {
        setupData()

        expect:
        userXTreatmentService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<UserXTreatment> userXTreatmentList = userXTreatmentService.list(max: 2, offset: 2)

        then:
        userXTreatmentList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        userXTreatmentService.count() == 5
    }

    void "test delete"() {
        Long userXTreatmentId = setupData()

        expect:
        userXTreatmentService.count() == 5

        when:
        userXTreatmentService.delete(userXTreatmentId)
        sessionFactory.currentSession.flush()

        then:
        userXTreatmentService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        UserXTreatment userXTreatment = new UserXTreatment()
        userXTreatmentService.save(userXTreatment)

        then:
        userXTreatment.id != null
    }
}
