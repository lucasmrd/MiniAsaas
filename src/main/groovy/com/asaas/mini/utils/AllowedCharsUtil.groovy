package com.asaas.mini.utils

class AllowedCharsUtil {

    private static final String ALLOWED_CHARS_REGEX = /[\p{L}\p{N}\s.,'!?:;()\-]+/

    static Boolean hasOnlyAllowedChars(String input) {
        return input ==~ ALLOWED_CHARS_REGEX
    }
}
