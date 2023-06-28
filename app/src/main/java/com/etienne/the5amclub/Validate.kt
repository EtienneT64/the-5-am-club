package com.etienne.the5amclub

class Validate {

    fun validateEmail(email: String): Boolean {
        return email.contains("@")
    }
}