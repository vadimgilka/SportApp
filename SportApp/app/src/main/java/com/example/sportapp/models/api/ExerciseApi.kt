package com.example.sportapp.models.api

import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.GroupPreview
import com.example.sportapp.models.DTO.exercise.ExerciseCreation
import com.example.sportapp.models.DTO.exercise.ExerciseUpdation
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ExerciseApi {

    @Multipart
    @Headers("Connection: keep-alive")
    @PATCH("api/exercises")
    suspend fun update(
        @Header("Authorization") bearer: String,
        @Part("id") id: RequestBody,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("muscleGroup") muscleGroup: RequestBody,
        @Part imagePart: MultipartBody.Part?,
        @Part("video") video: RequestBody?,
        ): ExerciseInfo

    @Multipart
    @Headers("Connection: keep-alive")
    @POST("api/exercises")
    suspend fun create(
        @Header("Authorization") bearer: String,
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("muscleGroup") muscleGroup: RequestBody,
        @Part imagePart: MultipartBody.Part?,
        @Part("video") video: RequestBody?,
    ): ExerciseInfo
}