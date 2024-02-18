package com.example.sportapp.models

abstract class AbstractApi {
    abstract var login: String
    abstract var password: String
    abstract var connectionStatus: Byte

    abstract public fun authorization()

    abstract public fun testConnection()

    abstract protected fun loadTable(vararg targetName: String)

    abstract protected fun updateTable(vararg targetName: String)

    abstract protected fun createTable(vararg table: String)

    abstract protected fun changeStatus(vararg actualState: Byte)

    abstract public fun getStatus()
}