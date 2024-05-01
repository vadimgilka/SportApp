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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.DTO.ExerciseInfo
import com.example.sportapp.view.controllers.ExerciseListsScreenController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun exertionList(nav: NavHostController, exercises: ExerciseListsScreenController) {
    val elementslist = remember {
        mutableStateListOf(listOf<ExerciseInfo>())
    }
    var items :  List<ExerciseInfo> = listOf()
    CoroutineScope(Dispatchers.IO).launch {
        items = exercises.getGroup(exercises.getCurrentGroup())
        elementslist.addAll(listOf(items))
    }
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
                if(elementslist.size>1) {
                    items(elementslist[1]) {
                        exerciseElement(
                            it.name,
                            "",
                            it.id,
                            nav,
                            true,
                            exercises
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}