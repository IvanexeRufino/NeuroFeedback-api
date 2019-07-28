package neurofeedback.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PermissionServiceSpec extends Specification {

    PermissionService permissionService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Permission(...).save(flush: true, failOnError: true)
        //new Permission(...).save(flush: true, failOnError: true)
        //Permission permission = new Permission(...).save(flush: true, failOnError: true)
        //new Permission(...).save(flush: true, failOnError: true)
        //new Permission(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //permission.id
    }

    void "test get"() {
        setupData()

        expect:
        permissionService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Permission> permissionList = permissionService.list(max: 2, offset: 2)

        then:
        permissionList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        permissionService.count() == 5
    }

    void "test delete"() {
        Long permissionId = setupData()

        expect:
        permissionService.count() == 5

        when:
        permissionService.delete(permissionId)
        sessionFactory.currentSession.flush()

        then:
        permissionService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Permission permission = new Permission()
        permissionService.save(permission)

        then:
        permission.id != null
    }
}
