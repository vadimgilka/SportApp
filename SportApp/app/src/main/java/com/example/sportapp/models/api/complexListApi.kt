package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.train.TrainInfo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface complexListApi {
    @Headers("Connection: keep-alive")
    @GET("/api/trains")
    suspend fun getExercises(
        @Query("page") page: Int,
        @Header("Authorization") bearer: String,
    ): List<TrainInfo>

}