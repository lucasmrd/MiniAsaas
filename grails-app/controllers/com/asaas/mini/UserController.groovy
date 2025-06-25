package com.asaas.mini

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

class UserController {

    UserService userService

    @Secured(['permitAll'])
    def index() { }

    @Secured(['permitAll'])
    def validate() {
        try {
            userService.validateUserParams(params)

            session.tempUserParams = [ username: params.username,
                                       password: params.password,
                                       passwordConfirmation: params.passwordConfirmation ]

            redirect(controller: 'customer', action: 'index')
        } catch (ValidationException e) {
            println "Error occurred: ${e.message}"
            redirect(action: "index", params: [error: "Failed to validate user"])
        }
    }
}
