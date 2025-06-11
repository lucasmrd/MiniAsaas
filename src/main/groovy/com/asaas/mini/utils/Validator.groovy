package com.asaas.mini.utils

import com.asaas.mini.Customer
import java.time.LocalDate
import java.util.Map

import grails.validation.ValidationException

public class Validator {

    public static final String EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"

    public static Boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 100
    }

    public static Boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX)
    }

    public static Boolean isValidCpf(String cpf) {
        if (!cpf) return false
        
        if (cpf.matches("\\d{11}")) return false
        if (cpf == cpf[0] * 11) return false

        def sum = 0
        for (int i = 0; i < 9; i++) {
            sum += cpf[i].toInteger() * (10 - i)
        }

        def firstDigit = (sum * 10) % 11
        if (firstDigit == 10) firstDigit = 0

        if (cpf[9].toInteger() != firstDigit) return false
        
        sum = 0
        for (int i = 0; i < 10; i++) {
            sum += cpf[i].toInteger() * (11 - i)
        }
        def secondDigit = (sum * 10) % 11
        if (secondDigit == 10) secondDigit = 0

        if (cpf[10].toInteger() != secondDigit) return false

        return true
    }

    public static Boolean isValidCnpj(String cnpj) {
        if (!cnpj) return false

        if (cnpj.length() != 14) return false
        
        if (cnpj == cnpj[0] * 14) return false

        def pesos1 = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
        def pesos2 = [6] + pesos1 
        
        def soma1 = 0
        for (int i = 0; i < 12; i++) {
            soma1 += cnpj[i].toInteger() * pesos1[i]
        }

        def digito1 = soma1 % 11
        digito1 = (digito1 < 2) ? 0 : 11 - digito1

        if (cnpj[12].toInteger() != digito1) return false

        def soma2 = 0
        for (int i = 0; i < 13; i++) {
            soma2 += cnpj[i].toInteger() * pesos2[i]
        }
        def digito2 = soma2 % 11
        digito2 = (digito2 < 2) ? 0 : 11 - digito2

        if (cnpj[13].toInteger() != digito2) return false

        return true
    }

    public static Boolean isValidCpfCnpj(String cpfCnpj) {
        return isValidCpf(cpfCnpj) || isValidCnpj(cpfCnpj)
    }

    public static Boolean isValidMobilePhone(String mobilePhone) {
        if (!mobilePhone) return false

        if (mobilePhone.size() != 11) return false

        if (!mobilePhone.matches("\\d{10,11}")) return false

        return true
    }

    public static Boolean isValidBirthDate(LocalDate birthDate) {
        if (!birthDate) return false

        LocalDate today = LocalDate.now()
        if (birthDate.isAfter(today)) return false

        return true
    }

    public static Boolean isValidPostalCode(String postalCode) {
        if (!postalCode) return false

        if (!postalCode.matches("\\d{8}")) return false

        return true
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

        if(!parsedParams.mobilePhone) {
            customer.errors.rejectValue("", null, "Campo telefone é obrigatório!")
        } else if (!parsedParams.mobilePhone.matches("\\d{10,11}")) {
            customer.errors.rejectValue("", null, "Telefone inválido!")
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

    private static Map sanitizeParams(Map parseInfo) {
        Map sanitizedParams = [:]
        sanitizedParams.name = parseInfo.name?.trim()
        sanitizedParams.email = parseInfo.email?.trim()
        sanitizedParams.mobilePhone = parseInfo.mobilePhone?.trim().replaceAll("\\D", "")
        sanitizedParams.cpfCnpj = parseInfo.cpfCnpj?.trim().replaceAll("\\D", "")
        sanitizedParams.birthDate = LocalDate.parse(parseInfo.birthDate)
        sanitizedParams.address = parseInfo.address?.trim()
        sanitizedParams.city = parseInfo.city?.trim()
        sanitizedParams.state = parseInfo.state?.trim()
        sanitizedParams.postalCode = parseInfo.postalCode?.trim().replaceAll("\\D", "")
        sanitizedParams.addressNumber = parseInfo.addressNumber
        sanitizedParams.addressComplement = parseInfo.addressComplement?.trim()

        return sanitizedParams
    }
}