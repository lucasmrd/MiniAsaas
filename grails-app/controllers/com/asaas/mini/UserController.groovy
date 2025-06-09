package com.asaas.mini

import com.asaas.mini.utils.BaseEntityUser

public class CustomerController extends BaseEntityUser {

    def index() {        
    }

    def saveCustomer() {
        try {
            String name = params.name
            String email = params.email
            String cpfCnpj = params.cpfCnpj
            String postalCode = params.postalCode
            String address = params.address
            Integer addressNumber = params.addressNumber
            String addressComplement = params.addressComplement
            String city = params.city
            String state = params.state
            Customer customer = customerService.createCustomer(name: name, email: email, cpfCnpj: cpfCnpj, postalCode: postalCode,
                                 address: address, addressNumber: addressNumber, addressComplement: addressComplement, city: city, state: state)                                
        } catch (Exception e) {
            render status: 500, text: 'Não foi possível salvar o cliente'
            console.error("Error saving customer: ${e.message}", e)
        }
    }

    def showCustomer() {
        try {
            Customer customer = Customer.get(params.id)
            if (!customer) {
                render status: 404, text: 'Customer not found'
            }
            return [customer: customer]
        } catch (Exception e) {
            render status: 500, text: 'Customer not found'
        }
    }
}