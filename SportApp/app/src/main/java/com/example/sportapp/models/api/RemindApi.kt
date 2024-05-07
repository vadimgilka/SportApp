package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.remind.RemindCreation
import com.example.sportapp.models.DTO.remind.RemindInfo
import com.example.sportapp.models.DTO.remind.RemindUpdation
import retrofit2.http.*

interface RemindApi {

    @Headers("Connection: keep-alive")
    @POST("api/reminds")
    suspend fun create(@Header("Authorization") bearer: String, remindCreation : RemindCreation) : RemindInfo;

    @Headers("Connection: keep-alive")
    @PUT("api/reminds")
    suspend fun update(@Header("Authorization") bearer: String, remindUpdation: RemindUpdation) : RemindInfo;

    @DELETE("api/reminds/{id}")
    suspend fun delete(@Header("Authorization") bearer: String, @Path("id") id: Int) : RemindInfo;

    @GET("api/reminds/{id}")
    suspend fun get(@Header("Authorization") bearer: String, @Path("id") id: Int): RemindInfo;

    @GET("api/reminds")
    suspend fun getMany(@Header("Authorization") bearer: String) : MutableList<RemindInfo>;
}