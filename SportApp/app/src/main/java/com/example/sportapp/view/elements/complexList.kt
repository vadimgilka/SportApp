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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")


@Composable
fun complexList(
    nav: NavHostController
)
{
    Scaffold(
        topBar = { searchAddNavBar ({ nav.navigate("exercise") }, {nav.navigate("updateExercise")}) }
    ) {
        Row(
            Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(horizontal = 15.dp, vertical = 65.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
        ) {
            LazyColumn(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                items(3){
                    complexElement()
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun complexElement()
{
    Button(modifier = Modifier.fillMaxWidth(), colors = ButtonColors(white, Color.Black, white, Color.Transparent), shape = RoundedCornerShape(8.dp), border = BorderStroke(1.dp, Color.Black),onClick = { /*TODO*/ }) {
        Row (Modifier.fillMaxWidth().padding(vertical = 5.dp), verticalAlignment = Alignment.CenterVertically){
            Text(text = "1.")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Наименование комплекса")
        }
    }
}