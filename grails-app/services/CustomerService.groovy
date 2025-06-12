package com.asaas.mini

import com.asaas.mini.Customer
import com.asaas.mini.utils.Validator

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
public class CustomerService {
    

    public Customer save(Map parseInfo) {

        Map parsedParams = Validator.sanitizeParams(parseInfo)
        Customer validateCustomer = Validator.validate(parsedParams)

        if (validateCustomer.hasErrors()) {
            throw new ValidationException("Erro ao salvar cliente", validateCustomer.errors)
        }

        Customer customer = new Customer()
        customer.name = parsedParams.name
        customer.email = parsedParams.email
        customer.cpfCnpj = parsedParams.cpfCnpj
        customer.mobilePhone = parsedParams.mobilePhone
        customer.birthDate = parsedParams.birthDate
        customer.postalCode = parsedParams.postalCode
        customer.address = parsedParams.address
        customer.addressNumber = parsedParams.addressNumber
        customer.addressComplement = parsedParams.addressComplement
        customer.city = parsedParams.city
        customer.state = parsedParams.state

        customer.save(flush: true, failOnError: true)

        return customer
    }
    
    private Customer validateCustomer(Map parsedParams) {
        Customer customer = new Customer()
        
        if(!Validator.isValidName(parsedParams.name)) {
            customer.errors.rejectValue("name", null, "Nome é obrigatório!")
        }

        if(!Validator.isValidEmail(parsedParams.email)) {
            customer.errors.rejectValue("email", null, "Email inválido!")
        }

        if(!Validator.isValidCpfCnpj(parsedParams.cpfCnpj)) {
            customer.errors.rejectValue("cpfCnpj", null, "CPF/CNPJ inválido!")
        }

        if(!Validator.isValidMobilePhone(parsedParams.mobilePhone)) {
            customer.errors.rejectValue("mobilePhone", null, "Telefone inválido!")
        }

        if(!Validator.isValidDate(parsedParams.birthDate)) {
            customer.errors.rejectValue("birthDate", null, "Data de nascimento inválida!")
        }

        if(!Validator.isValidPostalCode(parsedParams.postalCode)) {
            customer.errors.rejectValue("postalCode", null, "CEP inválido!")
        }
        
    }
}