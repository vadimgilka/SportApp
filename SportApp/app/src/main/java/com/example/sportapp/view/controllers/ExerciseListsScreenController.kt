package com.example.sportapp.view.controllers

import Model.SportAppApi
import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.models.DTO.GroupPreview
import com.example.sportapp.models.DTO.train.ExerciseTrainInfo
import com.example.sportapp.models.DTO.train.TrainExercise

class ExerciseListsScreenController(api: SportAppApi) {
    private var api: SportAppApi = api
    private var currentGroup = ""
    private var exertionsList = listOf<ExerciseInfo>()
    private var weight = 0
    private var approach = 0
    private var repetition = 0
    private var time = 0
    private lateinit var currentComplexExertion: ExerciseTrainInfo
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

    fun setComplexExertion(exercise: ExerciseTrainInfo){
        this.currentComplexExertion = exercise
        this.currentExertion = exercise.Exercise
        this.approach = exercise.approach
        if(exercise.weight == null || exercise.repetition == null || exercise.time == null) {
            this.weight = 0
            this.repetition = 0
            this.time = 0
        }else{
            this.weight = exercise.weight!!
            this.repetition = exercise.repetition!!
            this.time = exercise.time!!
        }
    }

    fun getComplexExertion(): ExerciseTrainInfo {
        return this.currentComplexExertion
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

    fun unsetComplexData(){
        if(this.weight!=null) {
            this.weight = 0
        }
        if(this.time!=null) {
            this.time = 0
        }
        if(this.repetition!=null) {
            this.repetition = 0
        }
        if(this.approach!=null) {
            this.approach = 0
        }
//        if(this.currentExertion != null){
//            this.currentComplexExertion = Exe
//        }
//        if(this.currentExertion != null) {
//            this.currentExertion = listOf<ExerciseInfo>()[0]
//        }
    }

    fun getWeight(): Int {
        return this.weight
    }

    fun setWeight(weight: Int) {
        this.weight = weight
    }

    fun getApproach(): Int {
        return this.approach
    }

    fun setApproach(approach: Int) {
        this.approach = approach
    }

    fun getRepetition(): Int {
        return this.repetition
    }

    fun setRepetition(repetition: Int) {
        this.repetition = repetition
    }

    fun getTime(): Int {
        return this.time
    }

    fun setTime(time: Int) {
        this.time = time
    }
}