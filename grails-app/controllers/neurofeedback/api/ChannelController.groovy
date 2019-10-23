package neurofeedback.api

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_PROFESSIONAL'])
class ChannelController {

    static Boolean patient = false
    static Boolean professional = false
    static Boolean administrator = false

    static String adminFriendlyName = "Canales de EEG"
    static String friendlyName = "Canales"

    ChannelService channelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond channelService.list(params), model:[channelCount: channelService.count()]
    }

    def show(Long id) {
        respond channelService.get(id)
    }

    def create() {
        respond new Channel(params)
    }

    def save(Channel channel) {
        if (channel == null) {
            notFound()
            return
        }

        try {
            channelService.save(channel)
        } catch (ValidationException e) {
            respond channel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'channel.label', default: 'Channel'), channel.id])
                redirect channel
            }
            '*' { respond channel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond channelService.get(id)
    }

    def update(Channel channel) {
        if (channel == null) {
            notFound()
            return
        }

        try {
            channelService.save(channel)
        } catch (ValidationException e) {
            respond channel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'channel.label', default: 'Channel'), channel.id])
                redirect channel
            }
            '*'{ respond channel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        channelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'channel.label', default: 'Channel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'channel.label', default: 'Channel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
