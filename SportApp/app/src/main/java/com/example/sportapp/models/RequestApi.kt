package Model

import Model.DTO.Response
import retrofit2.Call
import retrofit2.http.GET

interface RequestApi {
    @GET("/auth")
    fun getResponse(): Call <Response>
}