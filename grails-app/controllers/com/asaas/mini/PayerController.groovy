package com.asaas.mini

public class PayerController {

    def payerService
    def springSecurityService

    def index() {
        println "${springSecurityService.currentUser.customer}"
    }

    def save() {
        try {
            params.customer = springSecurityService.currentUser.customer
            Payer payer = payerService.save(params)
            render "Sucesso ao salvar pagador: ${payer.name} com ID: ${payer.id}"
        } catch (Exception e) {
            println "Error occurred: ${e.message}"
            redirect(action: "index", params: [error: "Failed to save payer"])
        }
     }
}
