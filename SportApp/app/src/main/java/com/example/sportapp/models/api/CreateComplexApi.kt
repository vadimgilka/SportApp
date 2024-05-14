package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.train.TrainCreation
import com.example.sportapp.models.DTO.train.TrainInfo
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CreateComplexApi {
    @Headers("Connection: keep-alive")
    @POST("/api/trains")
    suspend fun getExercises(
        @Body complex: TrainCreation,
        @Header("Authorization") bearer: String,
    ): TrainInfo
}