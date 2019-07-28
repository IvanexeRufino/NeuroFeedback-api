package neurofeedback.api

import grails.gorm.services.Service

@Service(TreatmentParameter)
interface TreatmentParameterService {

    TreatmentParameter get(Serializable id)

    List<TreatmentParameter> list(Map args)

    Long count()

    void delete(Serializable id)

    TreatmentParameter save(TreatmentParameter treatmentParameter)

}