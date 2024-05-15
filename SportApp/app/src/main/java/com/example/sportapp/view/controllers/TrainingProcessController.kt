package com.example.sportapp.view.controllers

import Model.SportAppApi
import android.os.CountDownTimer
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sportapp.models.DTO.train.ExerciseTrainInfo
import com.example.sportapp.models.DTO.train.TrainInfo

class TrainingProcessController(api: SportAppApi) {
    private lateinit var training: TrainInfo
    private lateinit var currentExercise: ExerciseTrainInfo
    private var timer: CountDownTimer ?= null
    private var minutes = 1
    private var seconds = 0
    private var currentExerciseIndex = 0

    fun setTrain(train: TrainInfo){
        training = train
    }

    fun setRestTime(minutes: Int, seconds: Int){
        //выбрали время отдыха
        this.seconds = seconds
        this.minutes = minutes
    }

    fun addApproach(){
        //добавили подход к текущему упражнению
        this.currentExercise.approach++
    }

    @Composable
    fun restTimerCall(nav: NavHostController){
        //Тут вызываем таймер отдыха, но скорее всего ничего не добавим
        timer?.cancel()
        timer = object : CountDownTimer((minutes*60+seconds).toLong()*1000, 1000){
            override fun onTick(p0: Long) {
            }

            override fun onFinish() {
                nav.navigate("trainMenu")
            }
        }.start()
    }

    fun switchToNextExercise(){
        //Тут у нас код для отправки данных о выполненном упражнении (количество подходов например)
        if(training.exercises.size > currentExerciseIndex+1) {
            currentExerciseIndex++
            currentExercise = training.exercises[currentExerciseIndex]
        }
    }

    fun getCurrentExercise(): ExerciseTrainInfo {
        if(!this::currentExercise.isInitialized){
            this.currentExercise = training.exercises[0]
        }
        return this.currentExercise
    }

    fun trainStarted(){
        //Тут мы отправляем хапрос о том какая треня началась
    }
}