package neurofeedback.api

import grails.gorm.services.Service

@Service(Stimulus)
interface StimulusService {

    Stimulus get(Serializable id)

    List<Stimulus> list(Map args)

    Long count()

    void delete(Serializable id)

    Stimulus save(Stimulus stimulus)

}