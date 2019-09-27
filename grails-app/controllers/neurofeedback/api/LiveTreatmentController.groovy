package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.*

@Transactional(readOnly = true)
@Secured(['ROLE_PROFESSIONAL'])
class LiveTreatmentController {

    static String friendlyName = "Ver tratamientos en vivo"

    def springSecurityService
    def treatmentStorageService
    def analysisService

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
        def start = new Date().getTime()

        def data = treatmentStorageService.getDataForTreatment(params.id, '3')
        AnalyzedData ad = analysisService.getDataAnalyzed(data)

        def end = new Date().getTime()
        log.info("took me " + (end - start))

        render(view: "main.gsp", model: [userTreatmentLive: userT, analyzedData: ad])
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
