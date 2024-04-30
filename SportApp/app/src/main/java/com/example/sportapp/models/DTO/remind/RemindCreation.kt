package com.example.sportapp.models.DTO.remind

data class RemindCreation(
    val time: Int,
    val period: Int,
    val biologicalAdditiveId: Int,
    val last_reception: String,
)
