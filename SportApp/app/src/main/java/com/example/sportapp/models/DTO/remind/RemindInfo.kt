package com.example.sportapp.models.DTO.remind

import com.google.firebase.messaging.FirebaseMessaging


data class RemindInfo(
    val id: Int,
    var time: Int, // переводи часы и минуты в минуты: 20:30 = 20 * 60 + 30
    val period: Int, // через сколько надо повторять прием
    val last_reception: String?, // последнее время приема
    var measure: Int,
    val count_reception: Int,
    val current_reception: Int,
    val createdAt: String,
    val updateAt: String,
    val token: String
) {

    companion object {
        fun default(): RemindInfo {
            return RemindInfo(
                -1,
                0,
                1,
                null,
                100,
                30,
                0,
                "sssss",
                "ssss",
                FirebaseMessaging.getInstance().token.toString()
            )
        }
    }
}