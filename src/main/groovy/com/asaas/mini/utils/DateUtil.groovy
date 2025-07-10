package com.asaas.mini.utils

import java.text.SimpleDateFormat

public class DateUtil {

    public static final Integer MAX_DUE_DATE_DAYS = 183
    public static final Integer MIN_AGE = 16

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
        if (!birthDate) return false

        Date limitDate = fromString("01/01/1900")
        if (birthDate.before(limitDate)) return false

        Date today = new Date()
        if (birthDate.after(today)) return false

        Calendar birthCal = Calendar.getInstance()
        birthCal.time = birthDate

        Calendar todayCal = Calendar.getInstance()
        todayCal.time = today

        Integer age = todayCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR)

        Integer currentMonth = todayCal.get(Calendar.MONTH)
        Integer birthMonth = birthCal.get(Calendar.MONTH)
        Integer currentDay = todayCal.get(Calendar.DAY_OF_MONTH)
        Integer birthDay = birthCal.get(Calendar.DAY_OF_MONTH)

        if (currentMonth < birthMonth || (currentMonth == birthMonth && currentDay < birthDay)) age--

        if (!(age >= MIN_AGE)) return false

        return true
    }

    public static Boolean isValidCompanyCreationDate(String companyCreationDate) {
        companyCreationDate = fromString(companyCreationDate)

        if (!companyCreationDate) return false

        Date today = new Date()
        if (companyCreationDate.after(today)) return false

        return true
    }

    public static Boolean isValidDueDate(Date dueDate) {
        if (!dueDate) return false

        if (isDateBeforeToday(dueDate)) return false

        if (isAfterMaxDueDate(dueDate)) return false

        return true
    }

    public static Boolean isValidPaymentDate(Date paymentDate, Date dueDate) {
        if (!paymentDate) return false

        return true
    }

    public static String getMaxBirthDate() {
        Calendar cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, -MIN_AGE)

        Date maxDate = cal.time

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy")

        return sdf.format(maxDate)
    }

    public static Boolean isDateBeforeToday(Date dateToCompare) {
        if (!dateToCompare) return false

        return truncateTime(dateToCompare).before(truncateTime(new Date()))
    }

    public static Boolean isAfterMaxDueDate(Date dueDate) {
        def today = truncateTime(new Date())

        Calendar cal = Calendar.getInstance()
        cal.setTime(today)
        cal.add(Calendar.DATE, MAX_DUE_DATE_DAYS)
        Date limit = cal.getTime()

        return truncateTime(dueDate).after(limit)
    }

    private static Date truncateTime(Date date) {
        Calendar cal = Calendar.getInstance()
        cal.setTime(date)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }
}