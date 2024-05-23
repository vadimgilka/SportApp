package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.models.DTO.train.ExerciseTrainInfo
import com.example.sportapp.view.controllers.ExerciseListsScreenController
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.ComplexListScreenController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun complexExercises(nav: NavHostController, complexController: ComplexListScreenController, exerciseController: ExerciseListsScreenController) {
    var train = complexController.getTrainExercises()
    Scaffold(
        topBar = {
            searchAddNavBar(
                { nav.navigate("exercise") },
                { nav.navigate("updateExercise") })
        }
    ) {
        Row(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(vertical = 65.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (train.exercises.size > 1) {
                    items(train.exercises) {
                        complexExerciseElement(it, nav = nav, exerciseController)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun complexExerciseElement(
    exercise: ExerciseTrainInfo,
    nav: NavHostController,
    exerciseController: ExerciseListsScreenController
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonColors(white, Color.Black, white, Color.Transparent),
        onClick = {
            exerciseController.setComplexExertion(exercise)
            if(exercise.weight != null) {
                exerciseController.setWeight(exercise.weight!!)
            }else if(exercise.time != null){
                exerciseController.setTime(exercise.time)
            }
            if(exercise.repetition != null){
                exerciseController.setRepetition(exercise.repetition!!)
            }
            nav.navigate("exerciseView")
        }) {
        Row(
            Modifier
                .fillMaxWidth(0.85f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp)
                    .border(1.dp, Color.Black, CircleShape)
            ) {
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = exercise.Exercise.name, fontSize = 19.sp, color = Color.Black)
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = if (exercise.id < 100) {
                    exercise.id.toString()
                } else {
                    "99+"
                }, fontSize = 19.sp, color = Color.Black
            )
        }
    }
}