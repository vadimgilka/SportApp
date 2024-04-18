package com.example.sportapp.models.DTO

data class ExerciseInfo(
    val id: Int,
    val name: String,
    val description: String,
    val image: String,
    val video: String,
    val author_id: Int,
    val createdAt: String,
    val updatedAt: String,
    val muscleGroup: String
)
