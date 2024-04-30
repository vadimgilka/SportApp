package com.example.sportapp.models.DTO.remind

data class RemindUpdation(
    val id : Int,
    val time: Int,
    val period: Int,
    val biologicalAdditiveId: Int,
    val last_reception: String,
)