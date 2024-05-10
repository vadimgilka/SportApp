package com.example.sportapp.view.controllers.bio

import Model.SportAppApi
import com.example.sportapp.models.DTO.remind.RemindCreation
import com.example.sportapp.models.DTO.remind.RemindInfo
import com.example.sportapp.models.DTO.remind.RemindUpdation

class RemindController(var api : SportAppApi) {

    suspend fun create(remindCreation: RemindCreation): RemindInfo? {
        return api.createRemind(remindCreation);
    }

    suspend fun update(remindUpdation: RemindUpdation): RemindInfo? {
        return api.updateRemind(remindUpdation);
    }

    suspend fun getMany(): List<RemindInfo>? {
        return api.getRemindList();
    }

    suspend fun getOne(id : Int): RemindInfo? {
        return api.getRemind(id);
    }

    suspend fun delete(id: Int): RemindInfo? {
        return api.deleteRemind(id);
    }
}