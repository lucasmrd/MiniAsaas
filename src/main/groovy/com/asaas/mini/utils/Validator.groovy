package com.asaas.mini.utils

import com.asaas.mini.Customer

import java.text.SimpleDateFormat
import java.util.Map

import grails.validation.ValidationException

public class Validator {

    public static final String EMAIL_REGEX = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"

    public static Boolean isValidName(String name) {
        if (!name?.trim()) return false

        name = name.trim().replaceAll(/\s+/, ' ')

        if (name.length() < 5 || name.length() > 100) return false

        if (!name.contains(' ')) return false

        if (!(name ==~ /^[\p{L}\p{M}'\- ]+$/)) return false

        return true
    }

    public static Boolean isValidCompanyName(String name) {
        if (!name?.trim()) return false

        name = name.trim().replaceAll(/\s+/, ' ')

        if (name.length() < 2 || name.length() > 150) return false

        if (!(name ==~ /^[\p{L}\p{M}'\- ]+$/)) return false

        return true
    }

    public static Boolean isValidEmail(String email) {
        if (!email?.trim()) return false
        email = email.trim().toLowerCase()

        if (email.length() > 254)  return false

        def parts = email.split('@')
        if (parts.size() != 2 || parts[0].size() > 64) return false

        if (!email.matches(EMAIL_REGEX)) return false

        return true
    }

    public static Boolean isValidCpf(String cpf) {
        if (!cpf) return false

        cpf = cpf.replaceAll("\\D", "")

        if (!cpf.matches("\\d{11}")) return false
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

        cnpj = cnpj.replaceAll("\\D", "")

        if (cnpj.length() != 14) return false
        
        if (cnpj == cnpj[0] * 14) return false

        def firstWeights = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
        def secondWeights = [6] + firstWeights
        
        def firstSum = 0
        for (int i = 0; i < 12; i++) {
            firstSum += cnpj[i].toInteger() * firstWeights[i]
        }

        def firstDigit = firstSum % 11
        firstDigit = (firstDigit < 2) ? 0 : 11 - firstDigit

        if (cnpj[12].toInteger() != firstDigit) return false

        def secondSum = 0
        for (int i = 0; i < 13; i++) {
            secondSum += cnpj[i].toInteger() * secondWeights[i]
        }
        def secondDigit = secondSum % 11
        secondDigit = (secondDigit < 2) ? 0 : 11 - secondDigit

        if (cnpj[13].toInteger() != secondDigit) return false

        return true
    }

    public static Boolean isValidCpfCnpj(String cpfCnpj) {
        return isValidCpf(cpfCnpj) || isValidCnpj(cpfCnpj)
    }

    public static Boolean isValidPhone(String phone) {
        if (!phone) return false

        phone = phone.replaceAll("\\D", "")

        if (!phone.matches(/\d{2}[2-5]\d{7}/)) return false

        if (phone.size() < 10 || phone.size() > 11) return false

        return true
    }

    public static Boolean isValidMobilePhone(String mobilePhone) {
        if (!mobilePhone) return false

        mobilePhone = mobilePhone.replaceAll("\\D", "")

        if (!mobilePhone.matches("\\d{2}9\\d{8}")) return false

        return true
    }

    public static Boolean isValidPostalCode(String postalCode) {
        if (!postalCode) return false

        postalCode = postalCode.replaceAll("\\D", "")

        if (!postalCode.matches("\\d{8}")) return false

        return true
    }
}