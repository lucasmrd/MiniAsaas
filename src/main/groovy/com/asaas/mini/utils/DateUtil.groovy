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
}