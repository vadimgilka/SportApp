package com.example.sportapp.view.controllers

import Model.SportAppApi
import android.util.Log
import com.example.sportapp.models.Excercise
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.math.log

class ExerciseListsScreenController(api: SportAppApi) {
    private var allGroups: HashMap<String, List<Excercise>> = HashMap<String, List<Excercise>> ()
    private lateinit var currentGroup: List<Excercise>
    private var api: SportAppApi = api
//    private lateinit var exercisesNeck: List<Excercise>
//    private lateinit var exercisesTrapezoid: List<Excercise>
//    private lateinit var exercisesShoulders: List<Excercise>
//    private lateinit var exercisesBreast: List<Excercise>
//    private lateinit var exercisesLats: List<Excercise>
//    private lateinit var exercisesBiceps: List<Excercise>
//    private lateinit var exercisesTriceps: List<Excercise>
//    private lateinit var exercisesForearms: List<Excercise>
//    private lateinit var exercisesMiddleBack: List<Excercise>
//    private lateinit var exercisesLoin: List<Excercise>

    public fun getExerciseInfo(group: String): Int {
        /*TODO: do in future*/
        return 10
    }

    public fun loadExercises(groups: List<String>){
        CoroutineScope(Dispatchers.IO).launch {
            val exerciseList = api.getExerciseList(1)
            Log.e(exerciseList.toString(), "tag2")
        }
      /*TODO: do in future*/
        allGroups.put("neck", listOf(Excercise("упражнение8", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck"), Excercise("упражнение7", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck"), Excercise("упражнение6", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck"), Excercise("упражнение5", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck"), Excercise("упражнение4", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck"), Excercise("упражнение3", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck"),Excercise("упражнение2", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck"),Excercise("упражнение1", "link", 45, 4, listOf("действие 1", "Шаг 2"), "neck")))
        allGroups.put("trapezoid", listOf(Excercise("упражнение8", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid"), Excercise("упражнение7", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid"), Excercise("упражнение6", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid"), Excercise("упражнение5", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid"), Excercise("упражнение4", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid"), Excercise("упражнение3", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid"),Excercise("упражнение2", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid"),Excercise("упражнение1", "link", 45, 4, listOf("действие 1", "Шаг 2"), "trapezoid")))
    }

    public fun getGroup(group: String): List<Excercise>{
        currentGroup = allGroups.get(group)!!
        return this.currentGroup
    }

    public fun getGroupTotal(group: String): Int? {
        return allGroups.get(group)?.size//this.currentGroup.size
    }
}