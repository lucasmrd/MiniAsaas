package com.asaas.mini

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured

@Secured(['isAnonymous()'])
class LoginController {

    def springSecurityService

    def auth() {
        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.getSuccessUrl()
            return
        }

        String postUrl = "${request.contextPath}/login/authenticate"

        render(view: 'auth', model: [postUrl: postUrl])
    }

    def authfail() {
        String postUrl = "${request.contextPath}/login/authenticate"

        String username = session['SPRING_SECURITY_LAST_USERNAME']

        String error = ''

        def exception = session['SPRING_SECURITY_LAST_EXCEPTION']
        if (exception) {
            error = exception.message
        }

        render(view: 'auth', model: [postUrl: postUrl, username: username, error: error])
    }
}