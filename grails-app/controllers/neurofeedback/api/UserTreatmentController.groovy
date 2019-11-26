package neurofeedback.api

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_PROFESSIONAL', 'ROLE_PATIENT', 'ROLE_ADMIN'])
class UserTreatmentController {

    static Boolean patient = true
    static Boolean professional = true
    static Boolean administrator = true

    static String friendlyName = "Historial de tratamientos"
    static String adminFriendlyName = "Historial de tratamientos"
    def springSecurityService

    UserTreatmentService userTreatmentService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        List<UserTreatment> history = getApplicableHistory()
        List pending = history.stream().filter() { userT ->
            userT.status == "Pending"
        }.collect()

        List finished = history.stream().filter() { userT ->
            userT.status == "Finished"
        }.collect()
        respond history, model:[userTreatmentPending: pending, userTreatmentPendingCount: pending.size(),
                                userTreatmentFinished: finished, userTreatmentFinishedCount: finished.size()]
    }
    def user(Integer id){
        User user = User.findById(id)
        List<UserTreatment> history = UserTreatment.findAllByUser(user)
        List pending = history.stream().filter() { userT ->
            userT.status == "Pending"
        }.collect()
        List finished = history.stream().filter() { userT ->
            userT.status == "Finished"
        }.collect()
        respond history, model:[userTreatmentPending: pending, userTreatmentPendingCount: pending.size(),
                                userTreatmentFinished: finished, userTreatmentFinishedCount: finished.size()] 
    }
    def show(Long id) {
        respond userTreatmentService.get(id)
    }

    def create() {
        List<User> patientUsers = getApplicableUsers(params)
        List<Treatment> treatments = Treatment.findAll()
        respond new UserTreatment(params), model:[patientUsers: patientUsers, treatments:treatments]
    }

    def save(UserTreatment userTreatment) {
        if (userTreatment == null) {
            notFound()
            return
        }

        try {
            userTreatment.status = "Pending"
            userTreatmentService.save(userTreatment)
        } catch (ValidationException e) {
            respond userTreatment.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userTreatment.label', default: 'UserTreatment'), userTreatment.id])
                redirect userTreatment
            }
            '*' { respond userTreatment, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userTreatmentService.get(id)
    }

    def update(UserTreatment userTreatment) {
        if (userTreatment == null) {
            notFound()
            return
        }

        try {
            userTreatmentService.save(userTreatment)
        } catch (ValidationException e) {
            respond userTreatment.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userTreatment.label', default: 'UserTreatment'), userTreatment.id])
                redirect userTreatment
            }
            '*'{ respond userTreatment, [status: OK] }
        }
    }

    def history(Long id){
        UserTreatment treatment = UserTreatment.findById(id)
        User usuario = User.findById(treatment.user.id)
        List<UserTreatment> tratamientos = UserTreatment.findAllByUserAndTreatmentAndStatus(usuario,treatment.treatment, "Finished")
        List<ChannelConfig> canales = ChannelConfig.findAllByTreatment(treatment.treatment)


        List<String> promedio_canales= treatment.channel_average.split(',').collect{it as String}
        //render promedio_canales
        respond new UserTreatment(params), model:[tratamiento: treatment,tratamientos:tratamientos,user:usuario,canales:canales,promedio_canales:promedio_canales]
    }
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userTreatmentService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userTreatment.label', default: 'UserTreatment'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userTreatment.label', default: 'UserTreatment'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

    private List<UserTreatment> getApplicableHistory() {
        User user = springSecurityService.getCurrentUser()

        if(user.role.authority == "ROLE_PROFESSIONAL") {
            return (User.findAllByAssignedDoctor(user) as List<User>)*.treatments.flatten() as List<Treatment>
        } else if (user.role.authority == "ROLE_ADMIN") {
            return (User.findAll() as List<User>)*.treatments.flatten() as List<Treatment>
        }

        return user.treatments
    }

    private List<User> getApplicableUsers(Map params) {
        User user = springSecurityService.getCurrentUser()
        Role patient = Role.findByAuthority("ROLE_PATIENT")

        if(user.role.authority == "ROLE_PROFESSIONAL") {
            return  User.findAllByRoleAndAssignedDoctor(patient, user)
        } else if (user.role.authority == "ROLE_ADMIN") {
            return (User.findAllByRole(patient) as List<User>)
        }
    }
}
