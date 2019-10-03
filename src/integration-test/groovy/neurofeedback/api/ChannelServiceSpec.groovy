package neurofeedback.api

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ChannelServiceSpec extends Specification {

    ChannelService channelService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Channel(...).save(flush: true, failOnError: true)
        //new Channel(...).save(flush: true, failOnError: true)
        //Channel channel = new Channel(...).save(flush: true, failOnError: true)
        //new Channel(...).save(flush: true, failOnError: true)
        //new Channel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //channel.id
    }

    void "test get"() {
        setupData()

        expect:
        channelService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Channel> channelList = channelService.list(max: 2, offset: 2)

        then:
        channelList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        channelService.count() == 5
    }

    void "test delete"() {
        Long channelId = setupData()

        expect:
        channelService.count() == 5

        when:
        channelService.delete(channelId)
        sessionFactory.currentSession.flush()

        then:
        channelService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Channel channel = new Channel()
        channelService.save(channel)

        then:
        channel.id != null
    }
}
