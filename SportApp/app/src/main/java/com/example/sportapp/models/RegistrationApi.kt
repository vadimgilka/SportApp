package Model

import Model.DTO.AppToken
import Model.DTO.Registration
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegistrationApi {
    @Headers("Content-Type: application/json")
    @POST("/auth/register")
    fun registrate(@Body registration: Registration): Call<AppToken>
}