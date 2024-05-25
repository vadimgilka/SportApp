package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.TrainingProcessController

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun trainMenu(nav: NavHostController, trainingProcessController: TrainingProcessController){
    trainingProcessController.trainStarted()
    var goRest by remember {
        mutableStateOf(false)
    }
    var isLast by remember {
        mutableStateOf(trainingProcessController.isLastExercise())
    }
    var currentExerciseInfo = trainingProcessController.getCurrentExercise()
    var currentWeight by remember {
        mutableStateOf(currentExerciseInfo.weight)
    }
    var currentRepetition by remember {
        mutableStateOf(currentExerciseInfo.repetition)
    }
    var currentApproach by remember {
        mutableStateOf(currentExerciseInfo.approach)
    }
    var currentName by remember {
        mutableStateOf(currentExerciseInfo.Exercise.name)
    }
    var currentDescription by remember {
        mutableStateOf(currentExerciseInfo.Exercise.description)
    }
    if(goRest){
        goRest = false
        nav.navigate("restTimer")
    }
    Scaffold (topBar = {
        goBackNavBar {
            nav.navigate("exercise")
        }
    }) {
        Column(
            modifier = Modifier
                .background(white)
                .fillMaxSize()
                .padding(top = 65.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            exercisePreview(currentName,currentDescription)
            Spacer(modifier = Modifier.height(10.dp))
            approachTable(currentWeight, currentRepetition, approach = currentApproach)
            Spacer(modifier = Modifier.height(10.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                ),
                colors = ButtonColors(blue, white, blue, Color.Transparent),
                shape = RoundedCornerShape(10.dp), onClick = {
                    goRest = true
                }) {
                Text(text = "Отдых", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                ),
                colors = ButtonColors(green, white, green, Color.Transparent),
                shape = RoundedCornerShape(10.dp), onClick = {
                    trainingProcessController.addApproach()
                    //currentExerciseInfo = trainingProcessController.getCurrentExercise()
                    currentApproach = trainingProcessController.getApproach()
                }) {
                Text(text = "Добавить подход", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                ),
                colors = ButtonColors(blue, white, blue, Color.Transparent),
                shape = RoundedCornerShape(10.dp), onClick = {
                    if(isLast){
                        trainingProcessController.clean()
                        currentApproach = 1
                        nav.navigate("exercise")
                    }else {
                        trainingProcessController.switchToNextExercise()
                        currentExerciseInfo = trainingProcessController.getCurrentExercise()
                        currentName = currentExerciseInfo.Exercise.name
                        currentDescription = currentExerciseInfo.Exercise.description
                        currentWeight = currentExerciseInfo.weight
                        currentRepetition = currentExerciseInfo.repetition
                        currentApproach = currentExerciseInfo.approach
                        isLast = trainingProcessController.isLastExercise()
                    }
                }) {
                if(isLast){
                    Text(text = "Завершить тренировку", fontSize = 15.sp)
                }else {
                    Text(text = "Следующее упражнение", fontSize = 15.sp)
                }
            }
        }
    }
}