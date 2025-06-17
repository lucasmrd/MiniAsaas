package com.asaas.mini.utils

import java.text.SimpleDateFormat

public class DateUtil {

    public static Date fromString(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")
            Date parsedDate = sdf.parse(dateStr)
            return parsedDate
        } catch (Exception e) {
            return null
        }
    }

    public static Boolean isValidBirthDate(Date birthDate) {

        int minAge = 16

        if (!birthDate) return false

        Date limitDate = fromString("01/01/1900")
        if (birthDate.before(limitDate)) return false

        Date today = new Date()
        if (birthDate.after(today)) return false

        Calendar birthCal = Calendar.getInstance()
        birthCal.time = birthDate

        Calendar todayCal = Calendar.getInstance()
        todayCal.time = today

        int age = todayCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR)

        int currentMonth = todayCal.get(Calendar.MONTH)
        int birthMonth = birthCal.get(Calendar.MONTH)
        int currentDay = todayCal.get(Calendar.DAY_OF_MONTH)
        int birthDay = birthCal.get(Calendar.DAY_OF_MONTH)

        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) age--

        if (!(age >= minAge)) return false

        return true
    }

    public static Boolean isValidCompanyCreationDate(String companyCreationDate) {
        companyCreationDate = fromString(companyCreationDate)

        if (!companyCreationDate) return false

        Date today = new Date()
        if (companyCreationDate.after(today)) return false

        return true
    }

    public static String getMaxBirthDate() {
        Integer minAge = 16

        Calendar cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, -minAge)

        Date maxDate = cal.time

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

        return sdf.format(maxDate)
    }
}