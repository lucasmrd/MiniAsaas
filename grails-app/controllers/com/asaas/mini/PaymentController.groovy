package com.asaas.mini

import com.asaas.mini.utils.BaseController
import grails.converters.JSON
import grails.validation.ValidationException

class PaymentController extends BaseController {

    def paymentService

    def springSecurityService

    def index() {
        redirect(action: "create")
    }

    def create() {
        List<Payer> payerList = Payer.list()

        return [payerList: payerList]
    }

    def validate() {
        try {
            paymentService.validate(params)

            render([status: 'SUCCESS'] as JSON)
        } catch (ValidationException e) {
            response.status = 400

            def fieldErrors = e.errors.fieldErrors.collectEntries { fieldError ->
                [(fieldError.field): message(error: fieldError)]
            }

            render([status: 'ERROR', errors: fieldErrors] as JSON)
        } catch (Exception e) {
            log.error("Erro inesperado na validação: ${e.message}", e)
            response.status = 500
            render(contentType: 'application/json') {
                [
                    status: 'FATAL',
                    errors: [general: 'Ocorreu um erro inesperado.']
                ]
            }
        }
    }

    def save() {
        try {
            params.customer = springSecurityService.currentUser.customer

            Payment payment = paymentService.save(params)

            render([
                status: 'SUCCESS',
                message: "Sucesso ao salvar a cobrança de ID: ${payment.id}",
                redirectUrl: createLink(controller: "dashboard", action: "index")
            ] as JSON)

        } catch (Exception e) {
            println "Error occurred: ${e.message}"
            redirect(action: "index", params: [error: "Failed to save payment"])
        }
    }

    def list() {
        List<Payment> paymentList = listPayment()

        return [paymentList: paymentList]
    }

    def loadTableContent() {
        Boolean success = true
        String content = ""
        Integer totalCount = 0
        String responseMessage = ""

        try {
            List<Payment> paymentList = listPayment()
            content = g.render(template:"/payment/templates/list/tableContent", model:[paymentList: paymentList])
            totalCount = paymentList.totalCount
        } catch(Exception exception) {
            success = false
            responseMessage = "Não foi possível atualizar a listagem."
        } finally {
            render([success: success, content: content, totalRecords: totalCount, message: responseMessage] as JSON)
        }
    }

    private List<Payment> listPayment() {
        Customer customer = springSecurityService.currentUser.customer

        Map searchParams = [:]
        searchParams.customer = customer

        if (params.name) {
            searchParams.term = params.name
        }

        return Payment.query(searchParams).list(max: getLimitPerPage(), offset: getCurrentPage())
    }
}
