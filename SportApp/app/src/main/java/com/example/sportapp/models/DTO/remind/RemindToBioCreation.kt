package com.example.sportapp.models.DTO.remind

data class RemindToBioCreation(
    val time: Int,
    val period: Int?,
    val measure : Int,
    val count_reception: Int?,
    val token : String
)
