package com.asaas.mini

import com.asaas.mini.Payer
import com.asaas.mini.utils.Validator
import com.asaas.mini.utils.DateUtil
import com.asaas.mini.enums.PersonType
import com.asaas.mini.enums.CompanyType

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors

@Transactional
public class PayerService {

    public Payer save(Map parseInfo) {

        Map parsedParams = sanitizeParams(parseInfo)
        Payer validatedPayer = validatePayer(parsedParams)

        if (validatedPayer.hasErrors()) {
            throw new ValidationException("Erro ao salvar cliente", validatedPayer.errors)
        }

        Payer payer = new Payer()
        payer.name = parsedParams.name
        payer.customer = parsedParams.customer
        payer.cpfCnpj = parsedParams.cpfCnpj
        payer.email = parsedParams.email
        payer.phone = parsedParams.phone
        payer.mobilePhone = parsedParams.mobilePhone
        payer.postalCode = parsedParams.postalCode
        payer.address = parsedParams.address
        payer.addressNumber = parsedParams.addressNumber
        payer.addressComplement = parsedParams.addressComplement
        payer.city = parsedParams.city
        payer.state = parsedParams.state

        payer.save(flush: true, failOnError: true)

        return payer
    }

    public Payer validatePayer(Map parsedParams) {

        Payer payer = new Payer()

        if (!Validator.isValidName(parsedParams.name)) {
            payer.errors.rejectValue("name", null, "Nome inválido!")
        }

        if (!Validator.isValidEmail(parsedParams.email)) {
            payer.errors.rejectValue("email", null, "Email inválido!")
        }

        if (!Validator.isValidCpfCnpj(parsedParams.cpfCnpj)) {
            payer.errors.rejectValue("cpfCnpj", null, "CPF/CNPJ inválido!")
        }

        if (!Validator.isValidPhoneNumber(parsedParams.phone, parsedParams.mobilePhone)) {
            payer.errors.rejectValue("phone", null, "É necessário informar um celular ou um telefone válido!")
        }

        if (!Validator.isValidPostalCode(parsedParams.postalCode)) {
            payer.errors.rejectValue("postalCode", null, "CEP inválido!")
        }

        return payer
    }

    public void validatePayerStep(Map params) {
        if (params.long('payerId')) {
            def payerId = params.long('payerId')
            if (!Payer.exists(payerId)) {
                Errors errors = new BeanPropertyBindingResult(params, 'payer')
                errors.rejectValue('payerId',
                        'payer.not.found',
                        'Cliente selecionado não encontrado')
                throw new ValidationException("Erro de validação do cliente", errors)
            }
        } else {
            Map parsedParams = sanitizeParams(params)
            Payer validatedPayer = validatePayer(parsedParams)

            if (validatedPayer.hasErrors())
                throw new ValidationException("Erro aovalidar dados do novo cliente", validatedPayer.errors)
        }
    }

    private static Map sanitizeParams(Map parseInfo) {

        Map sanitizedParams = [:]
        sanitizedParams.customer = parseInfo.customer
        sanitizedParams.name = parseInfo.name?.trim()
        sanitizedParams.email = parseInfo.email?.trim()
        sanitizedParams.phone = (parseInfo.phone?.trim())?.replaceAll("\\D", "")
        sanitizedParams.mobilePhone = (parseInfo.mobilePhone?.trim())?.replaceAll("\\D", "")
        sanitizedParams.cpfCnpj = (parseInfo.cpfCnpj?.trim())?.replaceAll("\\D", "")
        sanitizedParams.address = parseInfo.address?.trim()
        sanitizedParams.city = parseInfo.city?.trim()
        sanitizedParams.state = parseInfo.state?.trim()
        sanitizedParams.postalCode = (parseInfo.postalCode?.trim())?.replaceAll("\\D", "")
        sanitizedParams.addressNumber = parseInfo.addressNumber
        sanitizedParams.addressComplement = parseInfo.addressComplement?.trim()

        return sanitizedParams
    }
}
