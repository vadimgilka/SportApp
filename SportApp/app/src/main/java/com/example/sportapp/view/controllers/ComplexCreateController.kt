package com.example.sportapp.view.controllers

import Model.SportAppApi
import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.train.ExerciseTrainInfo
import com.example.sportapp.models.DTO.train.TrainCreation
import com.example.sportapp.models.DTO.train.TrainExerciseCreate
import com.example.sportapp.view.elements.ExerciseListItem

class ComplexCreateController(val api: SportAppApi) {
    lateinit var name: String
    lateinit var description: String
    private var exerciseList = mutableListOf<TrainExerciseCreate>()

    fun setNameDescription(name: String, description: String){
        this.name = name
        this.description = description
    }

    suspend fun loadExercisesList(): List<ExerciseInfo> {
        return this.api.getAllExercises()
    }

    fun addExercise(
        exerciseInfos: List<ExerciseInfo>,
        exercise: ExerciseListItem,
        tryCountInput: String,
        weightInput: String
    ) {
        exerciseInfos.forEach {
            if(it.name == exercise.getName() && it.id == exercise.getId()){
                exerciseList.add(exerciseList.size, TrainExerciseCreate(it.id, null, tryCountInput.toInt(), weightInput.toInt(), exerciseList.size+1))
            }
        }
    }

    fun isExercisesExist(): Boolean{
        if(this.exerciseList.size>0){
            return true
        }else{
            return false
        }
    }

    suspend fun createComplex(){
        val newTrain = TrainCreation(this.name, this.description, this.exerciseList)
        this.api.createComplex(newTrain)
    }
}