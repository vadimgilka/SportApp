package com.example.sportapp.view.controllers

import Model.SportAppApi
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.GroupPreview
import com.example.sportapp.models.Excercise
import com.example.sportapp.view.elements.exerciseView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.log

class ExerciseListsScreenController(api: SportAppApi) {
    private var api: SportAppApi = api
    private var currentGroup = ""
    private var exertionsList = listOf<ExerciseInfo>()
    private var currentExertion = ExerciseInfo(0,"","", "","", 0, "", "", "")

    public fun getExerciseInfo(group: String): Int {
        /*TODO: do in future*/
        return 10
    }

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

    @SuppressLint("CoroutineCreationDuringComposition")
    @Composable
    public fun getGroupTotal(group: String): Int? {
        var total by remember {
            mutableStateOf(0)
        }
        val extra = CoroutineScope(Dispatchers.IO).async {
//            val result = api.getExerciseGroupsCount() as MutableList<GroupPreview>
//            groupsAndTotals = result
//            for (i in 0..<groupsAndTotals.size) {
//                if (groupsAndTotals[i].muscleGroup == group) {
//                     return@async groupsAndTotals[i]._count
//                }
//            }
        }
        extra.invokeOnCompletion {
            if(it == null){
                total = extra.getCompleted() as Int
                Log.e("virtual", total.toString())
            }
        }
        Log.e("real", total.toString())
        return total
    }

}