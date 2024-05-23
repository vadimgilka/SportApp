package com.example.sportapp.models.DTO.bio

import com.example.sportapp.models.DTO.remind.RemindToBioCreation

data class BioAdditiveCreation(
    val name: String,
    val description: String,
    val reminds: MutableList<RemindToBioCreation>,
    var bioType: String
)
