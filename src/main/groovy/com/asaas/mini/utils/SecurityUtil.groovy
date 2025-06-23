package com.asaas.mini.utils

class SecurityUtil {

    private static final String PASSWORD_REGEX =
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,128}$/

    public static Boolean isValidPassword(String password, String passwordConfirmation) {
        if (!password) return false

        if (!passwordConfirmation) return false

        if (!password.matches(PASSWORD_REGEX)) return false

        if (password != passwordConfirmation) return false

        return true
    }
}
