package com.example.sportapp.models.DTO.remind


data class RemindInfo(
    val id : Int,
    val time : Int, // переводи часы и минуты в минуты: 20:30 = 20 * 60 + 30
    val period : Int, // через сколько надо повторять прием
    val last_reception : String, // последнее время приема
    val createdAt : String,
    val updateAt : String,
)