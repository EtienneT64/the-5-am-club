package com.etienne.the5amclub

class Validate {

    fun validateEmail(email: String): Boolean {
        if (email.isNotBlank()) {
            if (!email.contains(" ")) {
                if (email.contains("@")) {
                    if (email.contains(".")) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun validatePassword(password: String): Boolean {
        if (password.isNotBlank()) {
            if (!password.contains(" ")) {
                return true
            }
        }

        return false
    }
}