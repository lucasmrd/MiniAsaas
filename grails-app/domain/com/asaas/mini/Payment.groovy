package com.asaas.mini

import com.asaas.mini.enums.BillingType
import com.asaas.mini.enums.PaymentStatus
import com.asaas.mini.utils.BaseEntity

class Payment extends BaseEntity {

    public static final BigDecimal MIN_VALUE = 5.00

    Customer customer

    Payer payer

    BillingType billingType

    PaymentStatus status

    BigDecimal value

    String description

    Date dueDate

    Date paymentDate

    static constraints = {
        description nullable: true, blank: true
        paymentDate nullable: true
    }

    static namedQueries = {
        query {Map search ->
            eq('deleted', false)

            createAlias('payer', 'p')

            if (search.customer) {
                eq('customer', search.customer)
            }

            if (search.term) {
                or {
                    ilike('p.name', "%${search.term}%")
                    ilike('p.email', "${search.term}")
                }
            }
        }

        confirmedPayments {Map search ->
            eq('deleted', false)
            eq('status', PaymentStatus.CONFIRMED)

            createAlias('payer', 'p')

            if (search.customer) {
                eq('customer', search.customer)
            }

            if (search.term) {
                or {
                    ilike('p.name', "%${search.term}%")
                    ilike('p.email', "${search.term}")
                }
            }
        }

        excludedPayments {Map search ->
            eq('deleted', true)

            createAlias('payer', 'p')

            if (search.customer) {
                eq('customer', search.customer)
            }

            if (search.term) {
                or {
                    ilike('p.name', "%${search.term}%")
                    ilike('p.email', "${search.term}")
                }
            }
        }
    }
}
