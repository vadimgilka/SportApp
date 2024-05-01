package com.example.sportapp.models.DTO.train

data class TrainUpdation(
    val id : Int,
    val name : String,
    val description : String,
    val exercises : MutableList<ExerciseTrainInfo>,
)