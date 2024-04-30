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
import com.example.sportapp.models.DTO.GroupPreview
import com.example.sportapp.models.Excercise
import com.example.sportapp.view.elements.exerciseView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.math.log

class ExerciseListsScreenController(api: SportAppApi) {
    private var allGroups: HashMap<String, List<Excercise>> = HashMap<String, List<Excercise>>()
    private lateinit var currentGroup: List<Excercise>
    private lateinit var groupsAndTotals: MutableList<GroupPreview>
    private var api: SportAppApi = api

    public fun getExerciseInfo(group: String): Int {
        /*TODO: do in future*/
        return 10
    }

    suspend fun loadExercises(): List<GroupPreview> {
        var result: List<GroupPreview> = api.getExerciseGroupsCount()
        return result
    }

    public fun getGroup(group: String): List<Excercise> {
        currentGroup = allGroups.get(group)!!
        return this.currentGroup
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