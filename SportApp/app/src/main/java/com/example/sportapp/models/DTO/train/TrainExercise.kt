package com.example.sportapp.models.DTO.train

data class TrainExercise(
    val exerciseId: Int,
    val trainId: Int,
    val exerciseNumber: Int,
    val repetition: Int,
    val approach: Int,
    val weight: Int,
    val time: Int
)
