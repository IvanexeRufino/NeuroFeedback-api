package neurofeedback.api

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class PingControllerSpec extends Specification implements ControllerUnitTest<PingController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        when:
            controller.index()
        then:
            response.text == "pong"
    }
}
