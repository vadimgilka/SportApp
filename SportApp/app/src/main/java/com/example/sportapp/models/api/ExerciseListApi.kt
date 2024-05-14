package com.example.sportapp.models.api

import Model.DTO.AppToken
import Model.DTO.LoginRequest
import com.example.sportapp.models.DTO.ExerciseInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ExerciseListApi {
 @Headers("Connection: keep-alive")
 @GET("/api/exercises")
 suspend fun getExercises(@Query("page") page: Int, @Query("muscleGroup") group: String, @Header("Authorization") bearer: String): List<ExerciseInfo>

 @Headers("Connection: keep-alive")
 @GET("/api/exercises")
 suspend fun getAllExercises(@Header("Authorization") bearer: String): List<ExerciseInfo>
}