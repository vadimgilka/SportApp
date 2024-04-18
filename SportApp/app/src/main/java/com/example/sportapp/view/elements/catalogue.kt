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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun catalogue(nav: NavHostController) {
    Scaffold(
        topBar = { goBackNavBar { nav.navigate("exercise") } }
    ) {
        Row(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 35.dp, vertical = 65.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                for (i in  1..3)
                items(1) {
                    catalogueButton(i, nav)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}


@Composable
fun catalogueButton(mod: Int, nav:NavHostController) {
    Button(modifier = Modifier
        .fillMaxWidth()
        .height(110.dp),
        colors = if(mod.mod(2) == 0){ ButtonColors(green, white, green, Color.Transparent)}else{ButtonColors(blue, white, blue, Color.Transparent)},
        shape = RoundedCornerShape(15.dp), onClick = {
            nav.navigate("exerciseList")
        }) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                modifier = Modifier
                    .height(100.dp)
                    .padding(end = if (mod == 2){35.dp}else{20.dp}),
                painter = if(mod == 1){painterResource(id = R.drawable.arcticons_home_workouts)}else if (mod==2){painterResource(id = R.drawable.arcticons_plank_workout)}else{painterResource(id = R.drawable.arcticons_workouttime)},
                contentDescription = null
            )
            Text(text = if(mod == 1){"Упражнения"}else if(mod == 2){"Комплексы"}else{"Табата"}, fontSize = 15.sp)
        }
    }
}