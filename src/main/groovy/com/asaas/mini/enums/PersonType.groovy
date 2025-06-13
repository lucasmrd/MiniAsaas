package com.asaas.mini.enums

import grails.util.Holders

enum PersonType {
	LEGAL, NATURAL

    public Boolean isLegal() {
        return [PersonType.LEGAL].contains(this)
    }

    public Boolean isNatural() {
        return [PersonType.NATURAL].contains(this)
    }

	public String getLabel() {
		return Holders.applicationContext.getBean("messageSource").getMessage("personType.${this.toString()}.label", null, "", new Locale("pt","BR"))
	}

	public static PersonType convert(personType) {
		try {
			return personType as PersonType
		} catch(Exception e) {
			return null
		}
	}
}