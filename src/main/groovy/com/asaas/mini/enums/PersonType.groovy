package com.asaas.mini.enums

import grails.util.Holders

enum PersonType {
	LEGAL, NATURAL

    public Boolean isLegal() {
        return PersonType.LEGAL == this
    }

    public Boolean isNatural() {
        return PersonType.NATURAL == this
    }

	public String getLabel() {
		return Holders.applicationContext.getBean("messageSource").getMessage("personType.${this.toString()}.label", null, "", new Locale("pt","BR"))
	}

	public static PersonType convert(personType) {
		try {
			if (personType instanceof String) personType = personType.toUpperCase()
			
			return personType as PersonType
		} catch(Exception e) {
			return null
		}
	}
}