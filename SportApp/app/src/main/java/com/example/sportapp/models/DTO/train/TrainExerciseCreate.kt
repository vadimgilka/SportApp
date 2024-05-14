package com.example.sportapp.models.DTO.train

data class TrainExerciseCreate(
    val id: Int,
    val time: Int?, // в секундах
    var repetition: Int?,
    var weight: Int?,
    val exerciseNumber: Int,
)
