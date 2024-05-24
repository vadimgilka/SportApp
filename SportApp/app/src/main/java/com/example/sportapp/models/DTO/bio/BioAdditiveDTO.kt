package com.example.sportapp.models.DTO.bio

data class BioAdditiveDTO(
    val id : Int,
    var name : String,
    val description : String,
    var reminds : MutableList<RemindDto>,
    var bioType :  String,
)

data class RemindDto(
    var id : Int,
    var measure : Int,
    var time : String,
    var token: String
)
