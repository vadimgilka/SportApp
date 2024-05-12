package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.train.TrainExercise
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Query

interface UpdateTrainExerciseApi {
    @Headers("Connection: keep-alive")
    @PUT("/api/trains/exercise")
    suspend fun update(@Body params: TrainExercise,@Header("Authorization") bearer: String)
}