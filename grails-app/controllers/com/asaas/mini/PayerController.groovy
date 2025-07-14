package com.asaas.mini

import com.asaas.mini.Customer
import com.asaas.mini.utils.BaseController
import com.asaas.mini.utils.Validator

import grails.converters.JSON
import grails.validation.ValidationException

public class PayerController extends BaseController {

    def payerService
    def springSecurityService

    def index() { }

    def save() {
        try {
            params.customer = springSecurityService.currentUser.customer

            Payer payer = payerService.save(params)

            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
            redirect(action: "index", params: [error: "Failed to save payer"])
        }
    }

    def show() {
        Payer payer = Payer.get(params.id)

        if (payer) return [payer: payer]

        render "Pagador não encontrado"
    }

    def update() {
        try {
            def customer = springSecurityService.currentUser.customer

            Payer updatedPayer = payerService.update(params, customer)

            redirect(action: "show", id: updatedPayer.id)
        } catch (ValidationException e) {
            response.status = 400

            def fieldErrors = e.errors.fieldErrors.collectEntries { fe ->
                [(fe.field): message(error: fe)]
            }

            render([status: 'ERROR', errors: fieldErrors] as JSON)
        } catch (Exception e) {
            log.error("Erro ao atualizar cliente: ${e.message}", e)

            response.status = 500

            render([status: 'ERROR', message: e.message] as JSON)
        }
    }

    def delete() {
        try {
            def customer = springSecurityService.currentUser.customer

            payerService.delete(params.long('id'), customer)

            render([Status: 'SUCCESS', message: 'Cliente desativado com sucesso!'] as JSON)
        } catch (Exception e) {
            flash.message = "Erro ao remover cliente: ${e.message}"

            render([status: 'ERROR', message: e.message] as JSON)
        }
    }

    def list() {
        List<Payer> payerList = listPayer()

        return [payerList: payerList]
    }

    def loadTableContent() {
        Boolean success = true
        String content = ""
        Integer totalCount = 0
        String responseMessage = ""

        try {
            List<Payer> payerList = listPayer()
            content = g.render(template:"/payer/templates/list/tableContent", model:[payerList: payerList])
            totalCount = payerList.totalCount
        } catch(Exception exception) {
            success = false
            responseMessage = "Não foi possível atualizar a listagem."
        } finally {
            render([success: success, content: content, totalRecords: totalCount, message: responseMessage] as JSON)
        }
    }

    private List<Payer> listPayer() {
        Customer customer = springSecurityService.currentUser.customer
        Map searchParams = [
            customerId: springSecurityService.currentUser.customer.id
        ]

        if (params.name) {
            String onlyNumbers = params.name?.replaceAll("\\D+","")
            if (Validator.isValidCpfCnpj(onlyNumbers)) {
                searchParams.cpfCnpj = onlyNumbers
            } else {
                searchParams.name = params.name
            }
        }

        return Payer.query(searchParams).list(max: getLimitPerPage(), offset: getCurrentPage())
    }
}
