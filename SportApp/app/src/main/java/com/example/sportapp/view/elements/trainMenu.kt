package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun trainMenu(nav: NavHostController){
    var weightInput by remember {
        mutableStateOf("")
    }
    var countInput by remember {
        mutableStateOf("")
    }
    Scaffold (topBar = {
        goBackNavBar {
            nav.navigate("exercise")
        }
    }) {
        Column(
            modifier = Modifier
                .background(white)
                .fillMaxSize()
                .padding(top = 65.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            exercisePreview()
            Spacer(modifier = Modifier.height(20.dp))
            Row (
                Modifier
                    .fillMaxWidth()
                    .height(40.dp), horizontalArrangement = Arrangement.Center){
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.32f)
                        .padding(end = 30.dp)
                        .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                        .shadow(
                            elevation = 10.dp,
                            ambientColor = Color.Gray,
                            spotColor = Color.DarkGray,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                ) {
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(white)
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        value = weightInput,
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            weightInput = it
                        },
                        decorationBox = {
                                innerTextField ->
                            Row (
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box (Modifier.weight(1f)) {
                                    if(weightInput.isEmpty()){
                                        Text(text = "Вес кг.", color = iconGray)
                                    }else {
                                        innerTextField()
                                    }
                                }
                            }
                        })
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.68f)
                        .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(10.dp))
                        .shadow(
                            elevation = 10.dp,
                            ambientColor = Color.Gray,
                            spotColor = Color.DarkGray,
                            shape = RoundedCornerShape(15.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                ){
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(white)
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        value = countInput,
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            countInput = it
                        },
                        decorationBox = {
                                innerTextField ->
                            Row (
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box (Modifier.weight(1f)) {
                                    if(countInput.isEmpty()){
                                        Text(text = "Кол. повторений", color = iconGray)
                                    }else {
                                        innerTextField()
                                    }
                                }
                            }
                        })
                }
            }
            Spacer(modifier = Modifier.height(200.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                ),
                colors = ButtonColors(blue, white, blue, Color.Transparent),
                shape = RoundedCornerShape(10.dp), onClick = {

                }) {
                Text(text = "Отдых", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                ),
                colors = ButtonColors(green, white, green, Color.Transparent),
                shape = RoundedCornerShape(10.dp), onClick = {

                }) {
                Text(text = "Добавить подход", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                ),
                colors = ButtonColors(blue, white, blue, Color.Transparent),
                shape = RoundedCornerShape(10.dp), onClick = {

                }) {
                Text(text = "Следующее упражнение", fontSize = 15.sp)
            }
        }
    }
}