import Model.SportAppApi

class LoginScreenController {
    val api: SportAppApi

    constructor(api: SportAppApi) {
        this.api = api
    }

    public fun onLogin(login: String, password: String): String {
        if(validateLogin(login) and validatePassword(password)) {
            this.api.authorization(login, password)
            return this.api.getStatus()
        }else{
            return "wrong password or email("
        }
    }

    private fun validateLogin(login: String): Boolean{
        val loginPattern = Regex("^[a-zA-Z0-9_]+\$")
        return login.length >= 4 && login.matches(loginPattern)
    }

    private fun validatePassword(password: String): Boolean{
        val passwordPattern = Regex("^[a-zA-Z0-9_]+\$")
        return password.length >= 7 && password.matches(passwordPattern)
    }
}