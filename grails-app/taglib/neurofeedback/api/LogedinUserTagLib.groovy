package neurofeedback.api

class LogedinUserTagLib {
    static defaultEncodeAs = [taglib:'html']

    static namespace = 'neurofeedback'

    def springSecurityService


    def currentUserProps = { attrs ->
        def user = springSecurityService.getCurrentUser()
        if (user) {
            if (attrs.username) {
                out << user.username
            } else if (attrs.firstName) {
                out << user.firstName
            } else if (attrs.lastName) {
                out << user.lastName
            } else if (attrs.email) {
                out << user.email
            }
        }
    }

}
