package com.example.sportapp.view.controllers

import Model.SportAppApi

class RegistrationScreenController {
    val api: SportAppApi

    constructor(api: SportAppApi) {
        this.api = api
    }

    public fun onRegister(login: String, password: String, mail: String): String {
        if(validateLogin(login) and validatePassword(password) and validateEmail(mail)) {
            this.api.registration(login, mail, password)
            return "Authorized"//this.api.getStatus()
        }else{
            return "incorrect"
        }
    }

    private fun validateLogin(login: String): Boolean{
        val loginPattern = Regex("^[a-zA-Z0-9_]+\$")
        return login.length >= 4 && login.matches(loginPattern)
    }

    private fun validateEmail(email: String): Boolean{
        val emailPattern = Regex("^([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)\$")
        return email.matches(emailPattern)
    }

    private fun validatePassword(password: String): Boolean{
        val passwordPattern = Regex("^[a-zA-Z0-9_]+\$")
        return password.length >= 7 && password.matches(passwordPattern)
    }
}