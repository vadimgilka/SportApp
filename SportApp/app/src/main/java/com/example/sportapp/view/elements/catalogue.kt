package com.example.sportapp.view.elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.MainScreenController

@Composable
fun catalogue(controller: NavHostController) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(10) {
            catalogueButton()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
    defaultNavi(controller = controller)
}

@Preview
@Composable
fun catalogueButton(){
    Button(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 60.dp),
        colors = ButtonColors(blue, white, blue, Color.Transparent),
        shape = RoundedCornerShape(15.dp), onClick = {

        }) {
        Row (
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
            Icon(
                modifier = Modifier
                    .height(100.dp)
                    .padding(end = 20.dp),
                painter = painterResource(id = R.drawable.arcticons_home_workouts),
                contentDescription = null
            )
            Text(text = "Упражнения", fontSize = 15.sp)
        }
    }
}