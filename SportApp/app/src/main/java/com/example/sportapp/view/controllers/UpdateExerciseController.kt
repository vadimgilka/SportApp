package com.example.sportapp.view.controllers

import Model.SportAppApi
import com.example.sportapp.models.DTO.exercise.ExerciseCreation
import com.example.sportapp.models.DTO.exercise.ExerciseUpdation
import com.example.sportapp.models.DTO.train.ExerciseTrainInfo
import com.example.sportapp.models.DTO.train.TrainExercise
import com.example.sportapp.models.DTO.train.TrainInfo

class UpdateExerciseController(var api: SportAppApi) {
    private var isComplex = false
    private lateinit var editedExercise: ExerciseUpdation
    private lateinit var editedComplexExercise: ExerciseTrainInfo
    fun setIsComplex(isComplex: Boolean){
        this.isComplex = isComplex
    }
    fun setEditedCompexExercise(exercise: ExerciseTrainInfo){
        this.editedComplexExercise = exercise
    }

    fun getEditedCompexExercise(): ExerciseTrainInfo {
        return this.editedComplexExercise
    }

    fun setEditedExercise(exercise: ExerciseUpdation){
        this.editedExercise = exercise
    }

    fun isEditedExercise(): Boolean{
        return this::editedExercise.isInitialized
    }

    fun getEditedExercise(): ExerciseUpdation{
        return this.editedExercise
    }

    fun isComplexExercise(): Boolean {
        return isComplex
    }
    suspend fun createExercise(exercise: ExerciseCreation){
        val response = api.createExercise(exercise)
    }
    suspend fun updateExercise(exercise: ExerciseUpdation){
        val response = api.updateExercise(exercise)
    }

    fun translateGroupName(group: String): String{
        var res = group
        when(res){
            "Трапеции"-> res = "Trapezius"
            "Шея"-> res = "Neck"
            "Плечи"-> res = "Shoulders"
            "Грудь"-> res = "Chest"
            "Широчайшие"-> res = "Latissimus"
            "Трицепсы"-> res = "Triceps"
            "Бицепсы"-> res = "Biceps"
            "Предплечья"-> res = "Forearms"
            "Средняя часть спины"-> res = "MiddleBack"
            "Поясница"-> res = "LowerBack"
            "Trapezius"-> res = "Трапеции"
            "Neck"-> res = "Шея"
            "Shoulders"-> res = "Плечи"
            "Chest"-> res = "Грудь"
            "Latissimus"-> res = "Широчайшие"
            "Triceps"-> res = "Трицепсы"
            "Biceps"-> res = "Бицепсы"
            "Forearms"-> res = "Предплечья"
            "MiddleBack"-> res = "Средняя часть спины"
            "LowerBack"-> res = "Поясница"
        }
        return res
    }

    suspend fun updateTrainExercise(exercise: ExerciseTrainInfo){
        val time = exercise.time ?: 0
        val weight = exercise.weight ?: 0
        val repetition = exercise.repetition ?: 0
        val sendUpdate = TrainExercise(
            exercise.exerciseId,
            exercise.trainId,
            exercise.id,
            repetition,
            exercise.approach,
            weight,
            time
        )
        this.api.updateTrainExercise(sendUpdate)
    }
}