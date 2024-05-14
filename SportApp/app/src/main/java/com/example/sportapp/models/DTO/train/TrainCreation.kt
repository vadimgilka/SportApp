package com.example.sportapp.models.DTO.train

data class TrainCreation(
    val name : String,
    val description : String,
    val exercises : MutableList<TrainExerciseCreate>,
)
