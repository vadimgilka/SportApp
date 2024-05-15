package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@SuppressLint("SuspiciousIndentation")
@Composable
fun approachTable(currentWeight: Int?, currentRepetition: Int?, approach: Int) {
    Column(
        Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 10.dp)) {
        Row (horizontalArrangement = Arrangement.SpaceAround){
            Spacer(modifier = Modifier.fillMaxWidth(0.25f))
            Text(text = "Вес", color = Color.Black)
            Spacer(modifier = Modifier.fillMaxWidth(0.35f))
            Text(text = "Повторения", color = Color.Black)
        }
        LazyColumn(modifier = Modifier.padding(top = 10.dp), content = {
            for (i in 1..approach)
            item {
                Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = i.toString(), color = Color.Black)
                    Spacer(modifier = Modifier.width(70.dp))
                    Text(text = currentWeight.toString().plus(" кг."), color = Color.Black)
                    Spacer(modifier = Modifier.width(90.dp))
                    Text(text = currentRepetition.toString().plus(" раз"), color = Color.Black)
                }
            }
        })
    }
}