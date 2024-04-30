package com.example.sportapp.models

import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.GroupPreview
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ExerciseGroupsPreview {
    @Headers("Connection: keep-alive")
    @GET("api/exercises/count/")
    suspend fun getExercisesGroups(@Header("Authorization") bearer: String): List<GroupPreview>
}