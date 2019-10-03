package neurofeedback.api

import grails.gorm.services.Service

@Service(Channel)
interface ChannelService {

    Channel get(Serializable id)

    List<Channel> list(Map args)

    Long count()

    void delete(Serializable id)

    Channel save(Channel channel)

}