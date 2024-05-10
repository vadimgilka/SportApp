package com.example.sportapp.view.controllers.bio

import Model.SportAppApi
import com.example.sportapp.models.DTO.bio.BioAdditiveCreation
import com.example.sportapp.models.DTO.bio.BioAdditiveInfo
import com.example.sportapp.models.DTO.bio.BioAdditiveUpdation

class BioAdditiveController(var api : SportAppApi) {

    suspend fun create(bioAdditiveCreation: BioAdditiveCreation): BioAdditiveInfo? {
        return api.createBioAdditive(bioAdditiveCreation);
    }

    suspend fun update(bioAdditiveUpdation: BioAdditiveUpdation): BioAdditiveInfo? {
        return api.updateBioAdditive(bioAdditiveUpdation);
    }

    suspend fun getMany(): List<BioAdditiveInfo>? {
        return api.getBioAdditiveList();
    }

    suspend fun getOne(id : Int): BioAdditiveInfo? {
        return api.getBioAdditive(id);
    }

    suspend fun delete(id: Int): BioAdditiveInfo? {
        return api.deleteBioAdditive(id);
    }
}