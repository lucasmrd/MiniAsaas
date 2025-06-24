package com.asaas.mini

import grails.plugin.springsecurity.annotation.Secured

public class CustomerController {
    def customerService

    @Secured(['permitAll'])
    def index() {
        if (!flash.userFormValidated) {
            redirect(controller: 'user', action: 'index')
        }
    }

    @Secured(['permitAll'])
    def save() {        
        try {
            Map allParams = params + session.tempUserParams
            Customer customer = customerService.save(allParams)

            session.removeAttribute('tempUserParams')

            render "Sucesso ao salvar cliente: ${customer.name} com ID: ${customer.id}"
        } catch (Exception e) {
            println "Error occurred: ${e.message}"
            redirect(action: "index", params: [error: "Failed to save customer"])
        }
     }
}