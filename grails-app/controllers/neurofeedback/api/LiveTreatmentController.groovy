package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_PROFESSIONAL'])
class LiveTreatmentController {

    static String friendlyName = "Ver tratamientos en vivo"

    def springSecurityService

    static Boolean patient = false
    static Boolean professional = true
    static Boolean administrator = false

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<UserTreatment> history = getApplicableHistory()

        List live = history.stream().filter() { userT ->
            userT.status == "Live"
        }.collect()

        respond history, model:[userTreatmentLive: live, userTreatmentLiveCount: live.size()]
    }

    def live() {
        UserTreatment userT = UserTreatment.findById(params.id)
        log.info("IM BEING CALLED " + userT.status)

        render(view: "main.gsp", model: [userTreatmentLive: userT])
    }

    def data() {
        render(status: 200, contentType: 'application/json') {
            analysis 14
            ch1 1
            ch2 2
            ch3 3
            ch4 4
            ch5 5
            ch6 6
            ch7 7
            ch8 8
        }
    }

    private List<UserTreatment> getApplicableHistory() {
        User user = springSecurityService.getCurrentUser()

        if(user.role.authority == "ROLE_PROFESSIONAL") {
            return (User.findAllByAssignedDoctor(user) as List<User>)*.treatments.flatten() as List<Treatment>
        }

        return user.treatments
    }


}
