package com.asaas.mini.enums

import grails.util.Holders

enum BillingType {

    BANK_SLIP,
    PIX,
    CREDIT_CARD

    public String getLabel() {
        return Holders.applicationContext
                .getBean("messageSource")
                .getMessage(
                        "billingType.${this.toString()}.label",
                        null,
                        "",
                        new Locale("pt","BR"))
    }

    public static BillingType convert(billingType) {
        try {
            def value = billingType

            if (value instanceof List || value instanceof String[]) value = value[0]

            if (value instanceof String) value = value.toUpperCase()

            return value as BillingType
        } catch(Exception e) {
            return null
        }
    }
}