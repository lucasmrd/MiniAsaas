package com.asaas.mini.utils

abstract class BaseEntityUser extends BaseEntity {

    String name
    String email
    String cpfCnpj
    String postalCode
    String address
    Integer addressNumber
    String addressComplement
    String city
    String state
    
    static constraints = {
        name nullable: false, blank: false, maxSize: 255
        email nullable: false, blank: false, email: true, maxSize: 255
        cpfCnpj nullable: false, blank: false, maxSize: 20
        postalCode nullable: true, blank: true, maxSize: 10
        address nullable: true, blank: true, maxSize: 255
        addressNumber nullable: true, blank: true
        addressComplement nullable: true, blank: true, maxSize: 50
        city nullable: true, blank: true, maxSize: 100
        state nullable: true, blank: true, maxSize: 50
    }

}