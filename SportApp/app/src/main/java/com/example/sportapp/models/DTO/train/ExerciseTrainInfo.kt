package com.example.sportapp.models.DTO.train

import com.example.sportapp.models.DTO.ExerciseInfo

data class ExerciseTrainInfo(
    val id: Int,
    val exerciseId : Int,
    val trainId : Int,
    val repetition : Int,
    val approach : Int,
    val time : Int, // в секундах
    val Exercise : ExerciseInfo,
)