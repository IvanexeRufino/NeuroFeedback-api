package neurofeedback.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PermissionXRoleServiceSpec extends Specification {

    PermissionXRoleService permissionXRoleService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PermissionXRole(...).save(flush: true, failOnError: true)
        //new PermissionXRole(...).save(flush: true, failOnError: true)
        //PermissionXRole permissionXRole = new PermissionXRole(...).save(flush: true, failOnError: true)
        //new PermissionXRole(...).save(flush: true, failOnError: true)
        //new PermissionXRole(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //permissionXRole.id
    }

    void "test get"() {
        setupData()

        expect:
        permissionXRoleService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PermissionXRole> permissionXRoleList = permissionXRoleService.list(max: 2, offset: 2)

        then:
        permissionXRoleList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        permissionXRoleService.count() == 5
    }

    void "test delete"() {
        Long permissionXRoleId = setupData()

        expect:
        permissionXRoleService.count() == 5

        when:
        permissionXRoleService.delete(permissionXRoleId)
        sessionFactory.currentSession.flush()

        then:
        permissionXRoleService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PermissionXRole permissionXRole = new PermissionXRole()
        permissionXRoleService.save(permissionXRole)

        then:
        permissionXRole.id != null
    }
}
