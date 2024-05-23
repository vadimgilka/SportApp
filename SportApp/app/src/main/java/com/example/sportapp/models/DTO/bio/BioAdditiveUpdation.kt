package com.example.sportapp.models.DTO.bio

import com.example.sportapp.models.DTO.remind.RemindToBioCreation

data class BioAdditiveUpdation(
    val id : Int,
    val name : String,
    val description : String,
    val reminds: MutableList<RemindToBioCreation>,
    val bioType : String
)
