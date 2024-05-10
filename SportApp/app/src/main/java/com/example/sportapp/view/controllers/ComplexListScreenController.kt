package com.example.sportapp.view.controllers

import Model.SportAppApi
import com.example.sportapp.models.DTO.train.ExerciseTrainInfo
import com.example.sportapp.models.DTO.train.TrainInfo

class ComplexListScreenController(val api: SportAppApi) {
    lateinit var complexList: List<TrainInfo>
    private var selectedId = 0

    suspend fun loadList(): List<TrainInfo> {
        complexList = this.api.getComplexList(1)
        return complexList
    }

    fun setSelectId(id: Int){
        selectedId = id
    }

    fun getTrainExercises(): TrainInfo {
        complexList.forEach {
            if(it.id == selectedId){
                return it
            }
        }
        return TrainInfo(
            1, "", "", 1, "", "", mutableListOf<ExerciseTrainInfo>()
        )
    }
}