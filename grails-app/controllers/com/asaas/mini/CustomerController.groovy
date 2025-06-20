package com.asaas.mini

import com.asaas.mini.CustomerService

public class CustomerController {
    def customerService

    def index() { }

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