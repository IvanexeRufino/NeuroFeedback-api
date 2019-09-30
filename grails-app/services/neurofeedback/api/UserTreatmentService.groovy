package neurofeedback.api

import grails.gorm.services.Service

@Service(UserTreatment)
interface UserTreatmentService {

    UserTreatment get(Serializable id)

    List<UserTreatment> list(Map args)

    Long count()

    void delete(Serializable id)

    UserTreatment save(UserTreatment userTreatment)

}