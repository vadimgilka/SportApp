package com.example.sportapp.models.DTO.exercise

import java.io.File

data class ExerciseCreation(
    val name: String,
    val description: String,
    val image : File,
    val video : String,
    val muscleGroup : String,
    )