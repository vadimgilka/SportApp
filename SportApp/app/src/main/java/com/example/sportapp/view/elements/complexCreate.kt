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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun complexCreate()
{
    var showMessage by remember {
        mutableStateOf(false)
    }
    var algorhitmInput by remember {
        mutableStateOf("")
    }
    var youtubelinkInput by remember {
        mutableStateOf("")
    }
    var nameInput by remember {
        mutableStateOf("")
    }
    var weightInput by remember {
        mutableStateOf("")
    }
    var tryCountInput by remember {
        mutableStateOf("")
    }
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var selectedGroup by remember {
        mutableStateOf("Средняя часть спины")
    }
    Scaffold(
        topBar = {
            goBackNavBar {
                //navHostController.navigate("exercise")
            }
        },
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(55.dp))
            LazyColumn(modifier = Modifier
                .height(300.dp)
                .padding(horizontal = 30.dp), content = {
                items(15){
                    exerciseElementSelect(
                        title = "Упраженение",
                        count = 3
                    )
                }
            })
            Spacer(modifier = Modifier.height(20.dp))
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
                        .padding(horizontal = 15.dp, vertical = 15.dp),
                    value = weightInput,
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        weightInput = it
                    },
                    decorationBox = { innerTextField ->
                        Column {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(Modifier.weight(1f)) {
                                    if (weightInput.length < 1) {
                                        Text(text = "Вес. кг", color = iconGray)
                                    } else {
                                        innerTextField()
                                    }
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
                        .padding(horizontal = 15.dp, vertical = 15.dp),
                    value = tryCountInput,
                    maxLines = 1,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = {
                        tryCountInput = it
                    },
                    decorationBox = { innerTextField ->
                        Column {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(Modifier.weight(1f)) {
                                    if (tryCountInput.length < 1) {
                                        Text(text = "Количество повторений", color = iconGray)
                                    } else {
                                        innerTextField()
                                    }
                                }
                            }
                        }
                    })

            }
            Spacer(modifier = Modifier.height(50.dp))
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
                shape = RoundedCornerShape(15.dp), onClick = {
                    if (algorhitmInput.length < 6 || nameInput.length < 3) {
                        if (algorhitmInput.length > 0 || nameInput.length > 0) {
                            showMessage = true
                        }
                    } else {
                        CoroutineScope(Dispatchers.IO).launch {

                        }
                    }
                }) {
                Text(text = "Сохранить", fontSize = 15.sp)
            }
            if (showMessage) {
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Минимальная длинна названия 3, техники выполнения 6",
                        color = Color.Red,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}

@Composable
fun exerciseElementSelect(
    title: String,
    count: Int,
) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    Button(
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonColors(white, Color.Black, white, Color.Transparent),
        onClick = {
            isSelected = !isSelected
        }) {
        Row(
            Modifier
                .fillMaxWidth(0.85f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 40.dp)
                    .border(1.dp, Color.Black, CircleShape)
            ) {
                if(isSelected){
                    Box (Modifier.fillMaxSize().clip(RoundedCornerShape(25.dp)).background(blue)){

                    }
                }
            }
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = title, fontSize = 19.sp)
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = if (count < 100) {
                    count.toString()
                } else {
                    "99+"
                }, fontSize = 19.sp
            )
        }
    }
}