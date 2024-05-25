package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.models.DTO.train.TrainInfo
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.ComplexListScreenController
import com.example.sportapp.view.controllers.TrainingProcessController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun complexSelect(
    nav: NavHostController,
    complexListScreenController: ComplexListScreenController,
    trainingProcessController: TrainingProcessController
) {
    Scaffold(
        topBar = {
            searchAddNavBar ({ nav.navigate("exercise") }, {nav.navigate("complexAdd")})
        }
    ) {
        val elementslist = remember {
            mutableStateListOf(listOf<TrainInfo>())
        }
        CoroutineScope(Dispatchers.IO).launch {
            elementslist[0] = complexListScreenController.loadList()
        }
        Row(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 65.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)) {
                if(elementslist[0].isNotEmpty()) {
                    if(elementslist[0][0].id != -1) {
                        for (i in 0..<elementslist[0].size) {
                            item {
                                complexElementForTrainStart(
                                    item = elementslist[0][i],
                                    index = i + 1,
                                    nav,
                                    complexListScreenController,
                                    trainingProcessController
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun complexElementForTrainStart(
    item: TrainInfo,
    index: Int,
    nav: NavHostController,
    complexListScreenController: ComplexListScreenController,
    trainingProcessController: TrainingProcessController
)
{
    Button(modifier = Modifier.fillMaxWidth(), colors = ButtonColors(white, Color.Black, white, Color.Transparent), shape = RoundedCornerShape(8.dp), border = BorderStroke(1.dp, Color.Black),onClick = {
        complexListScreenController.setSelectId(item.id)
        trainingProcessController.setTrain(complexListScreenController.getTrainExercises())
        nav.navigate("timerSetUp")
    }) {
        Row (
            Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically){
            Text(text = index.toString().plus("."), color = Color.Black)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = item.name, color = Color.Black)
        }
    }
}