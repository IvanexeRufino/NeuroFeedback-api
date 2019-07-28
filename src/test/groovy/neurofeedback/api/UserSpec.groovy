package neurofeedback.api

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class UserSpec extends Specification implements DomainUnitTest<User> {

    def setup() {
    }

    def cleanup() {
    }

    void "firstName and lastName cant be larger than 50"() {
        when:
            domain.firstName = "this string is going to be larger than 30 which is the max size of it"
            domain.lastName = "this string is going to be larger than 30 which is the max size of it"
        then:
            !domain.validate(['firstName'])
            !domain.validate(['lastName'])
    }

    void "email has to have an email semantic"() {
        when:
            User userInvalid = new User(email: "holaaaaaaaaaa")
            domain.email = "hollaaaa@gmail.com"
        then:
            !userInvalid.validate(['email'])
            domain.validate(['email'])
    }
}
