package com.example.sportapp.models
import retrofit2.Call
import retrofit2.http.GET
abstract class AbstractApi {

    abstract var login: String
    abstract var password: String
    abstract var connectionStatus: Byte

    abstract public fun authorization(login: String, password: String)
    abstract public fun testConnection(): Boolean

    abstract public fun registration(username: String, email: String, password: String)

    abstract protected fun loadTable(vararg targetName: String)

    abstract protected fun updateTable(vararg targetName: String)

    abstract protected fun createTable(vararg table: String)

    abstract protected fun changeStatus(vararg actualState: Byte)

    abstract public fun getStatus(): String
}