package com.example.sportapp.view.elements.bio

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
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
import com.example.sportapp.view.elements.goBackNavBar

class Dosage(var name: String, var time: String, var count: Int) {

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun bioUpdateCreateView(nav: NavHostController) {
    var name by remember {
        mutableStateOf("")
    }
    var listOfDoaseges = remember {
        mutableStateListOf(Dosage("Дозирока 1", "8:00", 0))
    }
    Scaffold(
        topBar = {
            goBackNavBar {
                nav.navigate("bioAdd")
            }
        }
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
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
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    value = name,
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = {
                        name = it
                    },
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.weight(1f)) {
                                if (name.isEmpty()) {
                                    Text(text = "Наименование Бадов", color = iconGray)
                                } else {
                                    innerTextField()
                                }
                            }
                        }
                    })
            }
            Spacer(
                modifier = Modifier
                    .height(15.dp)
                    .background(white)
            )
            Text(text = "Параметры напоминания", fontSize = 18.sp, color = blue)
            Spacer(
                modifier = Modifier
                    .height(15.dp)
                    .background(white)
            )
            LazyColumn(content = {
                items(listOfDoaseges.toList()) {
                    dosageView(dosage = it)
                }
                item {
                    Row (modifier = Modifier
                        .fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Button(modifier = Modifier
                            .height(32.dp),
                            colors = ButtonColors(blue, white, blue, Color.Transparent),
                            shape = RoundedCornerShape(15.dp),
                            onClick = { listOfDoaseges.add(Dosage(name, "11:00", 0)) }) {
                            Text(text = "+", color = Color.White)
                        }
                    }
                        Spacer(modifier = Modifier.height(80.dp))
                }
            })
        }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp),
                colors = ButtonColors(green, white, iconGray, Color.Transparent),
                shape = RoundedCornerShape(15.dp), onClick = {
                    nav.navigate("pill")
                }) {
                Text(text = "Сохранить", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun dosageView(dosage: Dosage) {
    var time by remember {
        mutableStateOf("8:00")
    }
    var count by remember {
        mutableStateOf("")
    }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(start = 40.dp)
    ) {
        Text(text = dosage.name, color = Color.Black)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp), horizontalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.4f)
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
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                value = count,
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = {
                    count = it
                    dosage.count = it.toInt()
                },
                decorationBox = { innerTextField ->
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.weight(1f)) {
                            if (count.isEmpty()) {
                                Text(text = "Количество", color = iconGray)
                            } else {
                                innerTextField()
                            }
                        }
                    }
                })
        }
        Spacer(modifier = Modifier.width(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
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
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    value = time,
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = {
                        time = it
                        dosage.time = it
                    },
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.weight(1f)) {
                                innerTextField()
                            }
                        }
                    })
            }
    }
    Spacer(modifier = Modifier.width(15.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth(0.4f)
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

    }
}