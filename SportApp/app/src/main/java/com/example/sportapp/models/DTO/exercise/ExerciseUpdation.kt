package com.example.sportapp.models.DTO.exercise

import java.io.File

data class ExerciseUpdation(
    val id : Int,
    val name: String,
    val description: String,
    val image : File,
    val video : String,
    val muscleGroup : String,
)