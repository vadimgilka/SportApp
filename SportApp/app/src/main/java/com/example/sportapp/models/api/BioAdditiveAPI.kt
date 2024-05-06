package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.bio.BioAdditiveCreation
import com.example.sportapp.models.DTO.bio.BioAdditiveInfo
import com.example.sportapp.models.DTO.bio.BioAdditiveUpdation
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionCreation
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionInfo
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionUpdation
import retrofit2.http.*

interface BioAdditiveAPI {

    @Headers("Connection: keep-alive")
    @POST("api/biological-additives")
    suspend fun create(@Header("Authorization") bearer: String, bioAdditiveCreation : BioAdditiveCreation) : BioAdditiveInfo;

    @Headers("Connection: keep-alive")
    @PUT("api/biological-additives")
    suspend fun update(@Header("Authorization") bearer: String, bioAdditiveUpdation: BioAdditiveUpdation) : BioAdditiveInfo;

    @DELETE("api/biological-additives/{id}")
    suspend fun delete(@Header("Authorization") bearer: String, @Path("id") id: Int) : BioAdditiveInfo;

    @GET("api/biological-additives/{id}")
    suspend fun get(@Header("Authorization") bearer: String, @Path("id") id: Int): BioAdditiveInfo;

    @GET("api/biological-additives")
    suspend fun getMany(@Header("Authorization") bearer: String) : MutableList<BioAdditiveInfo>;
}