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
        def sampleSize = 2048
        def fs = 100

        log.info("im starting at " + start)
        def data = treatmentStorageService.getDataForTreatment(params.id, '3').take(sampleSize)
        def transformer = new FastFourierTransformer(DftNormalization.STANDARD)
        def dataTransformed = transformer.transform((data as double[]), TransformType.FORWARD)
        def sampledData = (dataTransformed as List).take((dataTransformed.size()/2) as int)
        def spd = []
        def frequencies = []

        double spectralPower
        float frecuency
        Map<String, List<Double>> powerBands = [:]
        powerBands['Total'] = []
        powerBands['Beta'] = []
        powerBands['Alpha'] = []
        powerBands['Theta'] = []
        powerBands['Delta'] = []

        sampledData.eachWithIndex { Complex entry, int i ->
            spectralPower = ((entry.abs() * entry.abs()) / 100000)
            frecuency = i * ((fs/2)/(sampleSize/2))

            spd.add(spectralPower)
            frequencies.add(frecuency)

            powerBands['Total'] += [spectralPower]

            if(frecuency >= 0.5 && frecuency < 4) {
                powerBands['Delta'] += [spectralPower]
            } else if(frecuency >= 4 && frecuency < 8) {
                powerBands['Theta'] += [spectralPower]
            } else if(frecuency >= 8 && frecuency < 12) {
                powerBands['Alpha'] += [spectralPower]
            } else if(frecuency >= 12 && frecuency < 30) {
                powerBands['Beta'] += [spectralPower]
            }
        } as List

        def end = new Date().getTime()

        log.info("im plotting at " + end)
        log.info("took me " + (end - start))

        render(view: "main.gsp", model: [userTreatmentLive: userT, dataToload: spd, freqs: frequencies, pb: powerBands])
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
