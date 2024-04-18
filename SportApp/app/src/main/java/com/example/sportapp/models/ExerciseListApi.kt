package com.example.sportapp.models

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
 @Headers("Connection: keep-alive", "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6InRlc3R1c2VyMCIsInN1YiI6MywiaWF0IjoxNzEzNDYwNzUxLCJleHAiOjE3MTM1NDcxNTF9.3_Dtc-3an5Y-QlMi2ikr8b4OdbASC77xg480eh6-gUo")
 @GET("/api/exercises")
 fun getExercises(@Query("page") page: Int): Call<MutableList<ExerciseInfo>>
}