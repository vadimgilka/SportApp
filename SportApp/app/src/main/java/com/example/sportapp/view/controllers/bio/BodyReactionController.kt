package com.example.sportapp.view.controllers.bio;

import Model.SportAppApi
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionCreation
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionInfo
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionUpdation

public class BodyReactionController(var api : SportAppApi) {

    suspend fun create(bodyReactionCreation: BodyReactionCreation): BodyReactionInfo? {
        return api.createBodyReaction(bodyReactionCreation);
    }

    suspend fun update(bodyReactionUpdation: BodyReactionUpdation): BodyReactionInfo? {
        return api.updateBodyReaction(bodyReactionUpdation);
    }

    suspend fun getMany(): List<BodyReactionInfo>? {
        return api.getBodyReactionList();
    }

    suspend fun getOne(id : Int): BodyReactionInfo? {
        return api.getBodyReaction(id);
    }

    suspend fun delete(id: Int): BodyReactionInfo? {
        return api.deleteBodyReaction(id);
    }
}
