package neurofeedback.api

import grails.gorm.services.Service

@Service(UserHistory)
interface UserHistoryService {

    UserHistory get(Serializable id)

    List<UserHistory> list(Map args)

    Long count()

    void delete(Serializable id)

    UserHistory save(UserHistory userHistory)

}