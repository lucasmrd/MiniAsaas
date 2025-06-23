package com.asaas.mini

import com.asaas.mini.utils.SecurityUtil
import com.asaas.mini.utils.Validator

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

import org.springframework.validation.BeanPropertyBindingResult
import org.springframework.validation.Errors

@Transactional
class UserService {

    public User buildUser(Map parseInfo) {
        Map parsedParams = sanitizeParams(parseInfo)
        User validatedUser = validateUser(parsedParams)

        if (validatedUser.hasErrors()) {
            throw new ValidationException("Erro ao validar user", validatedUser.errors)
        }

        User user = new User(username: parsedParams.username, password: parseInfo.password)

        return user
    }

    private User validateUser(Map parsedParams) {
        User user = new User()

        if (!Validator.isValidEmail(parsedParams.username)) {
            user.errors.rejectValue("username", null, "Email inválido!")
        }

        if (!SecurityUtil.isValidPassword(parsedParams.password, parsedParams.passwordConfirmation)) {
            user.errors.rejectValue("password", null, "Senha inválida ou diferentes")
        }

        if (User.findByUsername(parsedParams.username)) {
            user.errors.rejectValue('username', null, 'Este email já está cadastrado!')
        }

        return user
    }

    public void validateUserParams(Map params) {
        Map parsedParams = sanitizeParams(params)
        Errors errors = new BeanPropertyBindingResult(params, 'user')

        if (!Validator.isValidEmail(parsedParams.username)) {
            errors.rejectValue("username", null, "Email inválido!")
        }

        if (!SecurityUtil.isValidPassword(parsedParams.password, parsedParams.passwordConfirmation)) {
            errors.rejectValue("password", null, "Senha inválida ou diferentes")
        }

        if (User.findByUsername(parsedParams.username)) {
            errors.rejectValue('username', null, 'Este email já está cadastrado!')
        }

        if (errors.hasErrors()) {
            throw new ValidationException("Erro ao validar usuário", errors)
        }
    }

    private static Map sanitizeParams(Map parseInfo) {
        Map sanitizedParams = [:]
        sanitizedParams.username = parseInfo.username?.trim()?.toLowerCase()
        sanitizedParams.password = parseInfo.password
        sanitizedParams.passwordConfirmation = parseInfo.passwordConfirmation

        return sanitizedParams
    }
}