package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.view.controllers.ExerciseListsScreenController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun exertionList(nav: NavHostController, exercises: ExerciseListsScreenController) {
    val items = exercises.getGroup("neck")
    Scaffold(
        topBar = { searchAddNavBar { nav.navigate("exercise") } }
    ) {
        Row(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(vertical = 65.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()) {
                items(count = items.size, key = null){
                    exerciseElement(items.get(it).getName(), items.get(it).getRepeats(), nav, false);
                    Spacer(modifier = Modifier.height(20.dp))
                }

//                { item ->
//                    exerciseElement("Упражнение", item.getRepeats(), nav);
//                    Spacer(modifier = Modifier.height(20.dp))
//                }
            }
        }
    }
}