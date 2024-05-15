package com.example.sportapp.view.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.green


@Composable
fun exercisePreview(name: String, description: String) {
    Box(modifier = Modifier
        .background(Color.White)
        .fillMaxWidth()
        .padding(horizontal = 10.dp, vertical = 5.dp)
        .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
        .clip(RoundedCornerShape(10.dp)))
    {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Top) {
            Text(text = "Наименование", fontSize = 18.sp, color = blue)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = name, color = Color.Black)
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Описание", color = green)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = description, color = Color.Black)
        }
    }
}