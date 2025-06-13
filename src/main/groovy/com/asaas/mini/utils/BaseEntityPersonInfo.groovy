package com.asaas.mini.utils
package com.asaas.mini.enums.PersonType

abstract class BaseEntityPersonInfo extends BaseEntity {

    PersonType personType
    String name
    String email
    String cpfCnpj
    String mobilePhone
    String phone
    Date birthDate
    Date companyCreationDate
    String companyName
    String postalCode
    String address
    String addressNumber
    String addressComplement
    String city
    String state
    
    static constraints = {
        name nullable: false, blank: false, maxSize: 255
        companyName nullable: true, blank: true, maxSize: 255
        email nullable: false, blank: false, email: true, maxSize: 255
        phone nullable: true, blank: true, maxSize: 15
        mobilePhone nullable: false, blank: false, maxSize:15
        birthDate nullable: false, blank: false
        companyCreationDate nullable: true, blank: true
        cpfCnpj nullable: false, blank: false, maxSize: 20
        postalCode nullable: true, blank: true, maxSize: 10
        address nullable: true, blank: true, maxSize: 255
        addressNumber nullable: true, blank: true
        addressComplement nullable: true, blank: true, maxSize: 50
        city nullable: true, blank: true, maxSize: 100
        state nullable: true, blank: true, maxSize: 50
    }

}