package com.asaas.mini

import com.asaas.mini.Customer
import com.asaas.mini.utils.BaseEntityPersonInfo

public class Payer extends BaseEntityPersonInfo {
    Customer customer

    static namedQueries = {
        query { Map search ->

            eq("deleted", false)

            if (!search.containsKey("customerId")) {
                throw new RuntimeException("Payer.query(): o atributo [customerId] é obrigatório para executar a consulta.")
            }

            eq("customer.id", Long.valueOf(search.customerId))

            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id))
            }

            if (search.containsKey("name")) {
                like("name", "%" + search.name + "%")
            }

            if (search.containsKey("cpfCnpj")) {
                eq("cpfCnpj", search.cpfCnpj)
            }
        }
    }
}
