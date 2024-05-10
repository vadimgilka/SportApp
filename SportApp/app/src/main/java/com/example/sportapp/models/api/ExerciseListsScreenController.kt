package com.example.sportapp.models.api

import Model.SportAppApi
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.GroupPreview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class ExerciseListsScreenController(api: SportAppApi) {
    private var api: SportAppApi = api
    private var currentGroup = ""
    private var exertionsList = listOf<ExerciseInfo>()
    private var currentExertion = ExerciseInfo(0,"","", "","", 0, "", "", "")


    suspend fun loadExercises(): List<GroupPreview> {
        return api.getExerciseGroupsCount()
    }

    public fun setGroup(group: String){
        this.currentGroup = group
    }

    public fun setExertion(currentExertion: String){
        exertionsList.forEach{
            if(it.name == currentExertion){
                this.currentExertion = it
            }
        }
    }

    public fun getCurrentGroup(): String{
        return this.currentGroup
    }
    suspend fun getGroup(group: String): List<ExerciseInfo> {
        exertionsList = api.getExerciseList(1, group)
        return exertionsList
    }

    public fun getExertion(): ExerciseInfo{
        return this.currentExertion
    }

    public fun translateGroupName(group: String): String{
        var res = group
        when(res){
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

    fun setCurrentExertion(exercise: ExerciseInfo){
        this.currentExertion = exercise
    }
}