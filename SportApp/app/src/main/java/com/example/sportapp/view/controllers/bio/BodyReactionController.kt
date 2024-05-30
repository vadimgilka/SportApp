package com.example.sportapp.view.controllers.bio;

import Model.SportAppApi
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionCreation
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionInfo
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionUpdation

public class BodyReactionController(var api : SportAppApi) {

    var currentBodyReactionInfo : BodyReactionInfo = BodyReactionInfo(id = -1, name = "Название", detail = "Описание", null)

    fun setName(name : String){
        currentBodyReactionInfo.name = name
    }

    fun setBodyReaction(bodyReaction : BodyReactionInfo){
        currentBodyReactionInfo = bodyReaction
    }

    fun getName(): String {
        return currentBodyReactionInfo.name
    }

    fun setDetail(detail : String){
        currentBodyReactionInfo.detail = detail
    }

    fun getDetail(): String {
        return currentBodyReactionInfo.detail;
    }

    suspend fun create(bodyReactionCreation: BodyReactionCreation): BodyReactionInfo? {
        return api.createBodyReaction(bodyReactionCreation)
    }

    suspend fun update(bodyReactionUpdation: BodyReactionUpdation): BodyReactionInfo? {
        return api.updateBodyReaction(bodyReactionUpdation)
    }

    suspend fun getMany(): List<BodyReactionInfo>? {
        return api.getBodyReactionList()
    }

    suspend fun getOne(id : Int): BodyReactionInfo? {
        return api.getBodyReaction(id)
    }

    suspend fun delete(id: Int): BodyReactionInfo? {
        return api.deleteBodyReaction(id)
    }

    suspend fun makeOperation() {
        if(currentBodyReactionInfo.id <= 0){
            val bodyReactionCreation = BodyReactionCreation(currentBodyReactionInfo.name, currentBodyReactionInfo.detail)
            create(bodyReactionCreation)
        }
        else {
            val bodyReactionUpdation = BodyReactionUpdation(currentBodyReactionInfo.id, currentBodyReactionInfo.name, currentBodyReactionInfo.detail)
            update(bodyReactionUpdation)
        }
    }
}
