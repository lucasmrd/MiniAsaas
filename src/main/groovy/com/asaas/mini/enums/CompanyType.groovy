package com.asaas.mini.enums

import grails.util.Holders

enum CompanyType {
    ASSOCIATION, INDIVIDUAL, LIMITED_COMPANY, PERSONAL_COMPANY

    public Boolean isAssociation() {
        return [CompanyType.ASSOCIATION].contains(this)
    }

    public Boolean isIndividual() {
        return [CompanyType.INDIVIDUAL].contains(this)
    }

    public Boolean isLimitedCompany() {
        return [CompanyType.LIMITED_COMPANY].contains(this)
    }

    public Boolean isPersonalCompany() {
        return [CompanyType.PERSONAL_COMPANY].contains(this)
    }

    public String getLabel() {
        return Holders.applicationContext.getBean("messageSource").getMessage("companyType.${this.toString()}.label", null, "", new Locale("pt","BR"))
    }

    public static CompanyType convert(companyType) {
        try {
            return companyType as CompanyType
        } catch(Exception e) {
            return null
        }
    }
}