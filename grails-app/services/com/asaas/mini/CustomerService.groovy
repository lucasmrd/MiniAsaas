package com.asaas.mini

import com.asaas.mini.Customer
import com.asaas.mini.utils.Validator
import com.asaas.mini.utils.DateUtil
import com.asaas.mini.enums.PersonType
import com.asaas.mini.enums.CompanyType

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
public class CustomerService {

    public Customer save(Map parseInfo) {

        Map parsedParams = sanitizeParams(parseInfo)
        Customer validatedCustomer = validateCustomer(parsedParams)

        if (validatedCustomer.hasErrors()) {
            throw new ValidationException("Erro ao salvar cliente", validatedCustomer.errors)
        }

        Customer customer = new Customer()
        customer.personType = parsedParams.personType
        customer.name = parsedParams.name
        customer.companyName = parsedParams.companyName
        customer.email = parsedParams.email
        customer.cpfCnpj = parsedParams.cpfCnpj
        customer.companyType = parsedParams.companyType
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

    private Customer validateCustomer(Map parsedParams) {
        Customer customer = new Customer()

        if(parsedParams.personType.isNatural()) {
            if(!Validator.isValidName(parsedParams.name)) {
                customer.errors.rejectValue("name", null, "Nome inválido!")        
            }
            
            if(!DateUtil.isValidBirthDate(parsedParams.birthDate)) {
                customer.errors.rejectValue("birthDate", null, "Data de nascimento inválida!")
            }
        }

        if(parsedParams.personType.isLegal()) {
            if (!Validator.isValidCompanyName(parsedParams.companyName)) {
                customer.errors.rejectValue("companyName", null, "Nome da Empresa inválida!")
            }
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
            customer.errors.rejectValue("mobilePhone", null, "Celular inválido!")
        }

        if(!Validator.isValidPostalCode(parsedParams.postalCode)) {
            customer.errors.rejectValue("postalCode", null, "CEP inválido!")
        }

        return customer
    }

    private static Map sanitizeParams(Map parseInfo) {
        Map sanitizedParams = [:]
        sanitizedParams.personType = PersonType.convert(parseInfo.personType)
        sanitizedParams.name = parseInfo.name?.trim()
        sanitizedParams.email = parseInfo.email?.trim()
        sanitizedParams.birthDate = DateUtil.fromString(parseInfo.birthDate)
        sanitizedParams.phone = parseInfo.phone?.trim().replaceAll("\\D", "")
        sanitizedParams.mobilePhone = parseInfo.mobilePhone?.trim().replaceAll("\\D", "")
        sanitizedParams.companyName = parseInfo.companyName?.trim()
        sanitizedParams.companyType = CompanyType.convert(parseInfo.companyType)
        sanitizedParams.cpfCnpj = parseInfo.cpfCnpj?.trim().replaceAll("\\D", "")
        sanitizedParams.address = parseInfo.address?.trim()
        sanitizedParams.city = parseInfo.city?.trim()
        sanitizedParams.state = parseInfo.state?.trim()
        sanitizedParams.postalCode = parseInfo.postalCode?.trim().replaceAll("\\D", "")
        sanitizedParams.addressNumber = parseInfo.addressNumber
        sanitizedParams.addressComplement = parseInfo.addressComplement?.trim()

        return sanitizedParams
    }
}