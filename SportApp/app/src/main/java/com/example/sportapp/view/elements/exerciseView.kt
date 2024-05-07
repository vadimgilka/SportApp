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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.sportapp.R
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.view.controllers.ExerciseListsScreenController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun exerciseView(controller: ExerciseListsScreenController, nav: NavHostController){
    val exertion = controller.getExertion()
    Scaffold (topBar = {
        goBackNavBar {
            nav.navigate("exercise")
        }
    }) {
        LazyColumn(
            Modifier
                .background(Color.White)
                .fillMaxSize(1f)) {
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
                                contentDescription = "")
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
                        //for (i in 1..5)
                            Text(text = (1).toString().plus(". ").plus(exertion.description), color = Color.Black)
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
                        Text(text = "Вес кг.", color = Color.Black)
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
                        Text(text = "Количество повторений", color = Color.Black)
                    }
                }
                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}