package com.example.sportapp.models.DTO.bio

import com.example.sportapp.models.DTO.remind.RemindInfo

data class BioAdditiveInfo(
    val id : Int,
    var name : String,
    val description : String,
    val author_id : Int,
    val createdAt: String,
    val updatedAt: String,
    var reminds : MutableList<RemindInfo>,
    var bioType : String
)