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
import androidx.navigation.NavHostController
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.ComplexCreateController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun complexAdd(nav: NavHostController, controller: ComplexCreateController){
    var showMessage by remember {
        mutableStateOf(false)
    }
    var descriptionInput by remember {
        mutableStateOf("")
    }
    var nameInput by remember {
        mutableStateOf("")
    }
    Scaffold(
    topBar = {
        goBackNavBar {
            nav.navigate("complexList")
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
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    value = nameInput,
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = {
                        nameInput = it
                    },
                    decorationBox = { innerTextField ->
                        Column {
                            Text(text = "Название комплекса", fontSize = 18.sp, color = blue)
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(Modifier.weight(1f)) {
                                    if (nameInput.length < 1) {
                                        Text(text = "Введите название", color = iconGray)
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
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    value = descriptionInput,
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = {
                        descriptionInput = it
                    },
                    decorationBox = { innerTextField ->
                        Column {
                            Text(text = "Описание комплекса", fontSize = 18.sp, color = blue)
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(Modifier.weight(1f)) {
                                    if (nameInput.length < 1) {
                                        Text(text = "Введите описание", color = iconGray)
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
                    if (descriptionInput.length < 6 || nameInput.length < 3) {
                        if (descriptionInput.length > 0 || nameInput.length > 0) {
                            showMessage = true
                        }
                    } else {
                        controller.setNameDescription(nameInput, descriptionInput)
                        nav.navigate("complexCreate")
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
                        text = "Минимальная длинна названия 3, описания 6",
                        color = Color.Red,
                        fontSize = 10.sp
                    )
                }
            }
        }
    }
}