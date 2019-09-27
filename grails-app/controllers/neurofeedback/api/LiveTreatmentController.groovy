package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
@Transactional(readOnly = true)
@Secured(['ROLE_PROFESSIONAL'])
class LiveTreatmentController {

    static String friendlyName = "Ver tratamientos en vivo"

    def springSecurityService
    def treatmentStorageService

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
        def data = treatmentStorageService.getDataForTreatment(params.id, '3')

        render(view: "main.gsp", model: [userTreatmentLive: userT, dataToload: data])
    }

    def data() {
        def data = treatmentStorageService.getDataForTreatment(params.id, params.channel)

        Map response = [
                freqs: 5,
                psd: 16
        ]

        render(status: 200, contentType: 'application/json') {
            freqs 5
            psd 17
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
