package com.example.sportapp.models.DTO.train

data class TrainInfo (
    val id : Int,
    val name : String,
    val description : String,
    val author_id : Int,
    val createAt :String,
    val updateAt : String,
    val exercises : Array<ExerciseTrainInfo>,
)
