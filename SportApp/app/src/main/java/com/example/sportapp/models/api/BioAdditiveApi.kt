package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.bio.BioAdditiveCreation
import com.example.sportapp.models.DTO.bio.BioAdditiveInfo
import com.example.sportapp.models.DTO.bio.BioAdditiveUpdation
import retrofit2.http.*

interface BioAdditiveApi {

    @Headers("Connection: keep-alive")
    @POST("api/biological-additives/reminds")
    suspend fun create(
        @Header("Authorization") bearer: String, @Body bioAdditiveCreation: BioAdditiveCreation
    ): BioAdditiveInfo

    @Headers("Connection: keep-alive")
    @PUT("api/biological-additives/reminds")
    suspend fun update(
        @Header("Authorization") bearer: String, @Body bioAdditiveUpdation: BioAdditiveUpdation
    ): BioAdditiveInfo

    @DELETE("api/biological-additives/{id}")
    suspend fun delete(@Header("Authorization") bearer: String, @Path("id") id: Int): BioAdditiveInfo

    @GET("api/biological-additives/{id}")
    suspend fun get(@Header("Authorization") bearer: String, @Path("id") id: Int): BioAdditiveInfo

    @GET("api/biological-additives")
    suspend fun getMany(@Header("Authorization") bearer: String): MutableList<BioAdditiveInfo>
}