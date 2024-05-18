package com.example.sportapp.models.DTO.remind

data class RemindUpdation(
    val id : Int,
    val time: Int,
    val period: Int,
    val biologicalAdditiveId: Int,
    val measure : Int,
    val count_reception : Int,
    val last_reception: String,
    val token : String
)