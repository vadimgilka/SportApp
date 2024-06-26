package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.sportapp.R
import com.example.sportapp.models.DTO.exercise.ExerciseUpdation
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.view.controllers.ExerciseListsScreenController
import com.example.sportapp.view.controllers.UpdateExerciseController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun exerciseView(controller: ExerciseListsScreenController, nav: NavHostController, editController: UpdateExerciseController) {
    val exertion = controller.getExertion()
    if(controller.isSetComplexEcertion()) {
        editController.setEditedCompexExercise(controller.getComplexExertion())
    }
    Scaffold(topBar = {
        editNavBar({
            nav.navigate("exercise")
        },
            {
                if(controller.getApproach()>=1){
                    editController.setIsComplex(true)
                }
                else{
                    editController.setIsComplex(false)
                }
                editController.setEditedExercise(ExerciseUpdation(exertion.id, exertion.name, exertion.description, null, exertion.video, exertion.muscleGroup))
                nav.navigate("updateExercise")
            })
    }) {
        LazyColumn(
            Modifier
                .background(Color.White)
                .fillMaxSize(1f)
        ) {
            item {
                Spacer(modifier = Modifier.height(50.dp))
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                )
                {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(text = "Наименование", fontSize = 18.sp, color = blue)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(text = exertion.name, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
            item {
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = R.drawable.welcome,
                        contentScale = ContentScale.Crop,
                        contentDescription = ""
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                )
                {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(text = "Техника выполнения", fontSize = 18.sp, color = blue)
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            text = (1).toString().plus(". ").plus(exertion.description),
                            color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                )
                {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(text = if(controller.getWeight()!= null){controller.getWeight().toString().plus(" кг.")}else{ "Вес кг."}, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                        .clip(RoundedCornerShape(10.dp))
                )
                {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 10.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(text = if(controller.getRepetition()!= null){controller.getRepetition().toString().plus(" повторений")}else if(controller.getTime()!=null){ controller.getTime().toString().plus(" сек.")} else {"Количество повторений"}, color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}