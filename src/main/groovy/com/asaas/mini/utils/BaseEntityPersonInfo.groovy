package com.asaas.mini.utils

import java.time.LocalDate

abstract class BaseEntityPersonInfo extends BaseEntity {

    String name
    String email
    String cpfCnpj
    String mobilePhone
    LocalDate birthDate
    String postalCode
    String address
    String addressNumber
    String addressComplement
    String city
    String state
    
    static constraints = {
        name nullable: false, blank: false, maxSize: 255
        email nullable: false, blank: false, email: true, maxSize: 255
        mobilePhone nullable: false, blank: false, maxSize:15
        birthDate nullable: false, blank: false
        cpfCnpj nullable: false, blank: false, maxSize: 20
        postalCode nullable: true, blank: true, maxSize: 10
        address nullable: true, blank: true, maxSize: 255
        addressNumber nullable: true, blank: true
        addressComplement nullable: true, blank: true, maxSize: 50
        city nullable: true, blank: true, maxSize: 100
        state nullable: true, blank: true, maxSize: 50
    }

}