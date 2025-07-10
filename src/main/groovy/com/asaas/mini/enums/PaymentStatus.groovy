package com.asaas.mini.enums

import grails.util.Holders

enum PaymentStatus {

    PENDING,
    CONFIRMED,
    RECEIVED,
    OVERDUE,
    REFUNDED

    public String getLabel() {
        return Holders.applicationContext
                .getBean("messageSource")
                .getMessage(
                        "paymentStatus.${this.toString()}.label",
                        null,
                        "",
                        new Locale("pt","BR"))
    }

    public static PaymentStatus convert(paymentStatus) {
        try {
            if (paymentStatus instanceof String) paymentStatus = paymentStatus.toUpperCase()

            return paymentStatus as PaymentStatus
        } catch(Exception e) {
            return null
        }
    }
}