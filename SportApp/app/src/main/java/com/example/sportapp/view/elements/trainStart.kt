package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.white

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun trainStart(navi: NavHostController){
    Scaffold(
        topBar = { goOutNavBar() }
    ) {
        Column(modifier = Modifier
            .background(Color.White)
            .fillMaxSize()) {
            Spacer(modifier = Modifier.height(160.dp))
            GifImage(modifier = Modifier.size(320.dp).padding(start = 35.dp), image = R.drawable.trainstart)
            Spacer(modifier = Modifier.height(70.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp), colors = ButtonColors(green, white, green, Color.Transparent), shape = RoundedCornerShape(15.dp), onClick = {
                    navi.navigate("exerciseOptions")
            }
            ) {
                Text(text = "Начать тренировку", fontSize = 15.sp)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun trainOptions(nav: NavHostController){
    Scaffold(
        topBar = { goOutNavBar() }
    ) {
        Column(modifier = Modifier
            .background(Color.White)
            .fillMaxSize()) {
            Spacer(modifier = Modifier.height(100.dp))
            GifImage(modifier = Modifier.size(320.dp).padding(start = 35.dp), image = R.drawable.pullup)
            Spacer(modifier = Modifier.height(60.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp), colors = ButtonColors(green, white, green, Color.Transparent), shape = RoundedCornerShape(15.dp), onClick = {
                    nav.navigate("timerSetUp")
            }
            ) {
                Text(text = "Комплексная тренировка", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp), colors = ButtonColors(blue, white, blue, Color.Transparent), shape = RoundedCornerShape(15.dp), onClick = {

            }
            ) {
                Text(text = "Табата тренировка", fontSize = 15.sp)
            }
        }
    }
}