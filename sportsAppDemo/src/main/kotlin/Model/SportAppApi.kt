package Model

import Model.DTO.LoginRequest
import Model.DTO.Registration
import com.example.sportapp.models.AbstractApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Properties

class SportAppApi: AbstractApi() {
    private val CONFIG = "enviroment.properties"
    private val file = this::class.java.classLoader.getResourceAsStream(CONFIG)
    private val prop = Properties().apply { load(file)}
    private val url = getProperty("baseUrl")
    private val retrofit = Retrofit.Builder()
    private var status = "NO CONNECTION"
    override var login: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var password: String
        get() = TODO("Not yet implemented")
        set(value) {}
    override var connectionStatus: Byte
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
    *
    * Авторизация в api по логину и паролю
    *
    * @param username
    * @param password
    *
    */
    override fun authorization(username: String, password: String) {
        if(testConnection()) {
            val request = this.retrofit.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LoginApi::class.java)
            val res = request.auth(LoginRequest(username, password)).execute()
            if (res.code() == 201) {
                this.status = "Authorized"
            }
            println(res.body()?.access_token)
        }
    }

    /**
    *
    * Проверка соеденения с сервером
    *
    * @return результат проверки
    */
    override fun testConnection(): Boolean {
        val request = this.retrofit.baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RequestApi::class.java)
        val response = request.getResponse().execute()
        this.status = response.raw().message()
        println(response.raw().message())
        return response.code() == 401
    }

    /**
     *
     * Добавление нового пользователя
     *
     * @param username
     * @param email
     * @param password
     */
    override fun registration(username: String, email: String, password: String) {
        if(testConnection()) {
            val request = this.retrofit.baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RegistrationApi::class.java)
            val res = request.registrate(Registration(username, email, password)).execute()
            if (res.code() == 201) {
                this.status = "Authorized"
            }
            println(res.body()?.access_token)
        }
    }

    override fun loadTable(vararg targetName: String) {
        TODO("Not yet implemented")
    }

    override fun updateTable(vararg targetName: String) {
        TODO("Not yet implemented")
    }

    override fun createTable(vararg table: String) {
        TODO("Not yet implemented")
    }

    override fun changeStatus(vararg actualState: Byte) {
        TODO("Not yet implemented")
    }

    override fun getStatus(): String {
        return this.status
    }

    private fun getProperty(name: String): String = prop.getProperty(name)
}