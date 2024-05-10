package com.example.sportapp.models.api

import Model.DTO.AppToken
import Model.DTO.LoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {
    @Headers("Content-Type: application/json")
    @POST("/auth/login")
    fun auth(@Body request: LoginRequest): Call<AppToken>
}