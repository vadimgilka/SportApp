package com.example.sportapp.view.controllers

import Model.SportAppApi
import com.example.sportapp.models.DTO.exercise.ExerciseCreation
import com.example.sportapp.models.DTO.exercise.ExerciseUpdation

class UpdateExerciseController(var api: SportAppApi) {
    var isComplex = false
    fun setIsComplex(isComplex: Boolean){
        this.isComplex = isComplex
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
        }
        return res
    }
}