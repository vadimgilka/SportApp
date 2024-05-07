package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.bodyreaction.BodyReactionCreation
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionInfo
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionUpdation
import retrofit2.http.*

interface BodyReactionApi {

    @Headers("Connection: keep-alive")
    @POST("api/bodyreaction")
    suspend fun create(@Header("Authorization") bearer: String, bodyReactionCreation: BodyReactionCreation) : BodyReactionInfo;

    @Headers("Connection: keep-alive")
    @PUT("api/bodyreaction")
    suspend fun update(@Header("Authorization") bearer: String, bodyReactionUpdation: BodyReactionUpdation) : BodyReactionInfo;

    @DELETE("api/bodyreaction/{id}")
    suspend fun delete(@Header("Authorization") bearer: String, @Path("id") id: Int) : BodyReactionInfo;

    @GET("api/bodyreaction/{id}")
    suspend fun get(@Header("Authorization") bearer: String, @Path("id") id: Int): BodyReactionInfo;

    @GET("api/bodyreaction")
    suspend fun getMany(@Header("Authorization") bearer: String) : MutableList<BodyReactionInfo>;
}