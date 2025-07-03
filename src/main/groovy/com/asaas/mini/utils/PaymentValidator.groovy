package com.asaas.mini.utils

import com.asaas.mini.enums.BillingType

class PaymentValidator {

    public static Boolean isValidValue(BigDecimal value, BigDecimal minValue) {
        if (value == null) return false

        if (value <= 0) return false

        if (value < minValue) return false

        if (value.scale() > 2) return false

        return true
    }

    public static Boolean isValidDescription(String description) {
        if (description) {
            if (!description.matches("[\\p{L}\\p{N}\\s.,'!?:;()\\-]+")) return false
            if (description.length() > 255) return false
        }

        return true
    }

    public static Boolean isValidBillingType(BillingType billingType) {
        if (billingType == null) return false

        return true
    }
}
