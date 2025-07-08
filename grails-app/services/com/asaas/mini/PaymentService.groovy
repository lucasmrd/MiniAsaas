package com.asaas.mini

import com.asaas.mini.enums.BillingType
import com.asaas.mini.enums.PaymentStatus
import com.asaas.mini.utils.DateUtil
import com.asaas.mini.utils.PaymentValidator
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
class PaymentService {

    def payerService

    public void validate(Map parseInfo) {
        Map parsedParams = sanitizeParams(parseInfo)
        Payment validatedPayment = validatePayment(parsedParams)

        if (validatedPayment.hasErrors()) {
            throw new ValidationException("Erro ao validar payment", validatedPayment.errors)
        }
    }

    public Payment save(Map parseInfo) {
        Payer payer

        if (parseInfo.long('payerId')) {
            payer = Payer.get(parseInfo.long('payerId'))

            if (!payer) {
                throw new ValidationException("Cliente selecionado com ID ${parseInfo.long('payerId')} não foi encontrado.")
            }
        } else {
            payer = payerService.save(parseInfo)
        }

        parseInfo.payer = payer

        Map parsedParams = sanitizeParams(parseInfo)
        Payment validatedPayment = validatePayment(parsedParams)

        if (validatedPayment.hasErrors()) {
            throw new ValidationException("Erro ao validar payment", validatedPayment.errors)
        }

        Payment payment = new Payment()
        payment.customer = parsedParams.customer
        payment.payer = parsedParams.payer
        payment.billingType = parsedParams.billingType
        payment.status = PaymentStatus.PENDING
        payment.value = parsedParams.value
        payment.description = parsedParams.description
        payment.dueDate = parsedParams.dueDate

        payment.save(flush: true, failOnError: true)

        return payment
    }

    public void delete(Long id, Customer customer) {
        if (!id) {
            throw new IllegalArgumentException("ID da cobrança nulo.")
        }

        Payment payment = Payment.findByIdAndCustomer(id, customer)

        if (!payment) {
            throw new RuntimeException("Cobrança não encontrada!")
        }

        payment.deleted = true
        payment.markDirty('deleted')
    }

    public void restore(Long id, Customer customer) {
        if (!id) {
            throw new IllegalArgumentException("ID da cobrança nulo.")
        }

        Payment payment = Payment.findByIdAndCustomer(id, customer)

        if (!payment) {
            throw new RuntimeException("Cobrança não encontrada!")
        }

        payment.deleted = false
        payment.markDirty('deleted')
    }

    private Payment validatePayment(Map parsedParams) {
        Payment payment = new Payment()

        if (!PaymentValidator.isValidValue(parsedParams.value, payment.MIN_VALUE)) {
            payment.errors.rejectValue("value", null, "Valor inválido!")
        }

        if (!PaymentValidator.isValidDescription(parsedParams.description)) {
            payment.errors.rejectValue("description", null, "Descrição inválida")
        }

        if (!DateUtil.isValidDueDate(parsedParams.dueDate)) {
            payment.errors.rejectValue("dueDate", null, "Data limite inválida!")
        }

        if (!PaymentValidator.isValidBillingType(parsedParams.billingType)) {
            payment.errors.rejectValue("billingType", null, "Forma de pagamento inválida!")
        }

        return payment
    }

    private static Map sanitizeParams(Map parseInfo) {
        Map sanitizedParams = [:]

        if (parseInfo.customer) sanitizedParams.customer = parseInfo.customer

        if (parseInfo.payer) sanitizedParams.payer = parseInfo.payer

        def rawValue = parseInfo?.value
        sanitizedParams.value = rawValue ? rawValue.replace(',', '.').toBigDecimal() : null
        sanitizedParams.dueDate = DateUtil.fromString(parseInfo.dueDate)
        sanitizedParams.description = parseInfo.description?.trim()
        sanitizedParams.billingType = BillingType.convert(parseInfo.billingType)

        return sanitizedParams
    }
}
