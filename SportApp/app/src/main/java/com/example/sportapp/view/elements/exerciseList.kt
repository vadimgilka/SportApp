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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.models.DTO.GroupPreview
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.ExerciseListsScreenController
import com.example.sportapp.view.controllers.UpdateExerciseController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")


@Composable
fun exerciseList(
    nav: NavHostController,
    controller: ExerciseListsScreenController,
    updateExerciseController: UpdateExerciseController,
) {
    updateExerciseController.setIsComplex(false)
    controller.unsetComplexData()
    val elementslist = remember {
        mutableStateListOf(listOf<GroupPreview>())
    }
    var selectedGroup by remember {
        mutableStateOf("2")
    }
    var exerciseGroupsList: List<GroupPreview> = listOf()
    CoroutineScope(Dispatchers.IO).launch {
        exerciseGroupsList = controller.loadExercises()
        if (elementslist.size > 1) {
            elementslist[1] = exerciseGroupsList
        } else {
            elementslist.addAll(listOf(exerciseGroupsList))
        }
    }
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
                .padding(vertical = 55.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (elementslist.size > 1) {
                    if (elementslist[0].isNotEmpty()) {
                        items(elementslist[1]) {
                            selectedGroup = it.muscleGroup
                            exerciseElement(
                                controller.translateGroupName(it.muscleGroup),
                                selectedGroup,
                                it._count,
                                nav,
                                true,
                                controller
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun exerciseElement(
    title: String,
    groupValue: String,
    count: Int,
    nav: NavHostController,
    goExertion: Boolean,
    controller: ExerciseListsScreenController,
) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonColors(white, Color.Black, white, Color.Transparent),
        onClick = {
            if(groupValue!= null) {
                if (goExertion) {
                    controller.setGroup(groupValue)
                    nav.navigate("exertionList")
                } else {
                    controller.setExertion(title)
                    nav.navigate("exerciseView")
                }
            }
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
            Text(text = title, fontSize = 19.sp)
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = if (count < 100) {
                    count.toString()
                } else {
                    "99+"
                }, fontSize = 19.sp
            )
        }
    }
}