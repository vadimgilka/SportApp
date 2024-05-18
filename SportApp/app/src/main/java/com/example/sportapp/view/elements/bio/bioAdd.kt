package com.example.sportapp.view.elements.bio

import android.annotation.SuppressLint
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.elements.goBackNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun bioAdd(nav: NavHostController) {
    Scaffold(
        topBar = { goBackNavBar {
            nav.navigate("pill")
        } }
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(vertical = 65.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp),
                colors = ButtonColors(blue, white, blue, Color.Transparent),
                shape = RoundedCornerShape(15.dp), onClick = {
                    nav.navigate("bioUpdateCreateView")
                }) {
                Text(text = "Таблетки", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp),
                colors = ButtonColors(green, white, blue, Color.Transparent),
                shape = RoundedCornerShape(15.dp), onClick = {
                    nav.navigate("bioUpdateCreateView")
                }) {
                Text(text = "Порошки", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp),
                colors = ButtonColors(iconGray, white, iconGray, Color.Transparent),
                shape = RoundedCornerShape(15.dp), onClick = {
                    nav.navigate("bioUpdateCreateView")
                }) {
                Text(text = "Заметки", fontSize = 15.sp)
            }
        }
    }
}