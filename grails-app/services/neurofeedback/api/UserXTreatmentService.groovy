package neurofeedback.api

import grails.gorm.services.Service

@Service(UserXTreatment)
interface UserXTreatmentService {

    UserXTreatment get(Serializable id)

    List<UserXTreatment> list(Map args)

    Long count()

    void delete(Serializable id)

    UserXTreatment save(UserXTreatment userXTreatment)

}