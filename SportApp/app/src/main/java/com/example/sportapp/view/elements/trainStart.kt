package com.example.sportapp.view.elements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.white

@Composable
fun trainStart(){
    Spacer(modifier = Modifier.height(520.dp))
    Button(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 60.dp), colors = ButtonColors(green, white, green, Color.Transparent), shape = RoundedCornerShape(15.dp), onClick = {

    }
    ) {
        Text(text = "Начать тренировку", fontSize = 15.sp)
    }
}