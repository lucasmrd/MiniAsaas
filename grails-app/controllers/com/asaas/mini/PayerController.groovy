package com.asaas.mini

import com.asaas.mini.utils.BaseEntityUser

public class PayerController extends BaseEntityUser {
    
    def index() {     
    }

    def savePayer() {
        try {
            String payerName = params.payerName
            String payerEmail = params.payerEmail
            String payerCpfCnpj = params.payerCpfCnpj
            String payerPostalCode = params.payerPostalCode
            String payerAddress = params.payerAddress
            Integer payerAddressNumber = params.payerAddressNumber
            String payerAddressComplement = params.payerAddressComplement
            String payerCity = params.payerCity
            String payerState = params.payerState
        } catch (Exception e) {
            render status: 500, text: 'Não foi possível salvar o pagador'
            console.error("Error saving payer: ${e.message}", e)
        }    
    }

    def showPayer() {
        try {
            Payer payer = Payer.get(params.id)
            if (!payer) {
                render status: 404, text: 'Payer not found'
            } 
            return [payer: payer]
        } catch (Exception e) {
            render status: 500, text: 'Payer not found'
        }
    }    
}