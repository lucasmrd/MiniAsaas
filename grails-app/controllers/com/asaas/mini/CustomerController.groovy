package com.asaas.mini

import com.asaas.mini.Customer

public class CustomerController {
    def customerService
    def index() { }

    def save() {        
        try {
            Customer customer = customerService.save(params)
            render "Sucesso ao salvar cliente: ${customer.name} com ID: ${customer.id}"
        } catch (Exception e) {
            println "Error occurred: ${e.message}"
            redirect(action: "index", params: [error: "Failed to save customer"])
        }
     }
}