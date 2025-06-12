package com.asaas.mini

import com.asaas.mini.Customer
import com.asaas.mini.utils.Validator
import com.asaas.mini.utils.DateUtil

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
public class CustomerService {

    public Customer save(Map parseInfo) {

        Map parsedParams = sanitizeParams(parseInfo)
        Customer validateCustomer = validate(parsedParams)

        if (validateCustomer.hasErrors()) {
            throw new ValidationException("Erro ao salvar cliente", validateCustomer.errors)
        }

        Customer customer = new Customer()
        customer.name = parsedParams.name
        customer.companyName = parsedParams.companyName
        customer.email = parsedParams.email
        customer.cpfCnpj = parsedParams.cpfCnpj
        customer.phone = parsedParams.phone
        customer.mobilePhone = parsedParams.mobilePhone
        customer.birthDate = parsedParams.birthDate
        customer.companyCreationDate = parsedParams.companyCreationDate
        customer.postalCode = parsedParams.postalCode
        customer.address = parsedParams.address
        customer.addressNumber = parsedParams.addressNumber
        customer.addressComplement = parsedParams.addressComplement
        customer.city = parsedParams.city
        customer.state = parsedParams.state

        customer.save(flush: true, failOnError: true)

        return customer
    }

    private static Customer validate(Map parsedParams) {
        Customer customer = new Customer()

        if(!parsedParams.name) {
            customer.errors.rejectValue("", null, "Campo nome é obrigatório!")
        }

        if(!parsedParams.email) {
            customer.errors.rejectValue("", null, "Campo email é obrigatório!")
        } else if (!parsedParams.email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
            customer.errors.rejectValue("", null, "Email inválido!")
        }

        if(!parsedParams.cpfCnpj) {
            customer.errors.rejectValue("", null, "Campo CPF/CNPJ é obrigatório!")
        } else if (!parsedParams.cpfCnpj.matches("\\d{11}|\\d{14}")) {
            customer.errors.rejectValue("", null, "CPF/CNPJ inválido!")
        }

        if(!parsedParams.phone) {
            customer.errors.rejectValue("", null, "Campo telefone é obrigatório!")
        } else if (!parsedParams.phone.matches("\\d{10,11}")) {
            customer.errors.rejectValue("", null, "Telefone inválido!")
        }

        if(!parsedParams.mobilePhone) {
            customer.errors.rejectValue("", null, "Campo celular é obrigatório!")
        } else if (!parsedParams.mobilePhone.matches("\\d{10,11}")) {
            customer.errors.rejectValue("", null, "Celular inválido!")
        }

        if(!parsedParams.birthDate) {
            customer.errors.rejectValue("", null, "Campo data de nascimento é obrigatório!")
        } else if (!parsedParams.birthDate) {
            customer.errors.rejectValue("", null, "Data de nascimento inválida!")
        }

        if(!parsedParams.postalCode) {
            customer.errors.rejectValue("", null, "Campo CEP é obrigatório!")
        } else if (!parsedParams.postalCode.matches("\\d{5}-?\\d{3}")) {
            customer.errors.rejectValue("", null, "CEP inválido!")
        }

        if(!parsedParams.address) {
            customer.errors.rejectValue("", null, "Campo endereço é obrigatório!")
        }

        if(!parsedParams.addressNumber) {
            customer.errors.rejectValue("", null, "Campo número do endereço é obrigatório!")
        } else if (!parsedParams.addressNumber.isInteger()) {
            customer.errors.rejectValue("", null, "Número do endereço inválido!")
        }

        if(!parsedParams.addressComplement) {
            customer.errors.rejectValue("", null, "Complemento do endereço deve ter no máximo 50 caracteres!")
        }

        if(!parsedParams.city) {
            customer.errors.rejectValue("", null, "Campo cidade é obrigatório!")
        }

        if(!parsedParams.state) {
            customer.errors.rejectValue("", null, "Campo estado é obrigatório!")
        }

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

        if(!Validator.isValidPhone(parsedParams.phone)) {
            customer.errors.rejectValue("phone", null, "Telefone inválido!")
        }

        if(!Validator.isValidMobilePhone(parsedParams.mobilePhone)) {
            customer.errors.rejectValue("mobilePhone", null, "Telefone inválido!")
        }

        if(!Validator.isValidBirthDate(parsedParams.birthDate)) {
            customer.errors.rejectValue("birthDate", null, "Data de nascimento inválida!")
        }

        if(!Validator.isValidPostalCode(parsedParams.postalCode)) {
            customer.errors.rejectValue("postalCode", null, "CEP inválido!")
        }

        return customer
    }

    private static Map sanitizeParams(Map parseInfo) {
        Map sanitizedParams = [:]
        sanitizedParams.name = parseInfo.name?.trim()
        sanitizedParams.email = parseInfo.email?.trim()
        sanitizedParams.phone = parseInfo.phone?.trim().replaceAll("\\D", "")
        sanitizedParams.mobilePhone = parseInfo.mobilePhone?.trim().replaceAll("\\D", "")
        sanitizedParams.cpfCnpj = parseInfo.cpfCnpj?.trim().replaceAll("\\D", "")
        sanitizedParams.birthDate = DateUtil.fromString(parseInfo.birthDate)
        sanitizedParams.address = parseInfo.address?.trim()
        sanitizedParams.city = parseInfo.city?.trim()
        sanitizedParams.state = parseInfo.state?.trim()
        sanitizedParams.postalCode = parseInfo.postalCode?.trim().replaceAll("\\D", "")
        sanitizedParams.addressNumber = parseInfo.addressNumber
        sanitizedParams.addressComplement = parseInfo.addressComplement?.trim()

        return sanitizedParams
    }
}