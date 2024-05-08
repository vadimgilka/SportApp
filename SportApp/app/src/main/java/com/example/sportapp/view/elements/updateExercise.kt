package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import android.util.Log
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.models.DTO.exercise.ExerciseCreation
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.errorRed
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.UpdateExerciseController
import com.google.gson.annotations.Expose
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun updateExercise(
    navHostController: NavHostController, controller: UpdateExerciseController
) {
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
                navHostController.navigate("exercise")
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
                Text(text = "Наименование", fontSize = 18.sp, color = blue)
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
                            Text(text = "Наименование", fontSize = 18.sp, color = blue)
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(Modifier.weight(1f)) {
                                    if (nameInput.length < 1) {
                                        Text(text = "Наименование", color = iconGray)
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
                    .height(25.dp)
                    .background(white)
            )
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(horizontal = 60.dp)
                .shadow(
                    elevation = 10.dp,
                    ambientColor = Color.DarkGray,
                    spotColor = Color.DarkGray,
                    shape = RoundedCornerShape(10.dp)
                ),
                colors = ButtonColors(blue, white, blue, Color.Transparent),
                shape = RoundedCornerShape(15.dp), onClick = {
                    isExpanded = true
                }) {
                Text(text = "Группа мышц: ".plus(selectedGroup), fontSize = 15.sp)
            }
            DropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Шея"
                        isExpanded = false
                    },
                    text = { Text("Шея") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Трапеции"
                        isExpanded = false
                    },
                    text = { Text("Трапеции") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Плечи"
                        isExpanded = false
                    },
                    text = { Text("Плечи") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Грудь"
                        isExpanded = false
                    },
                    text = { Text("Грудь") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Широчайшие"
                        isExpanded = false
                    },
                    text = { Text("Широчайшие") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Бицепсы"
                        isExpanded = false
                    },
                    text = { Text("Бицепсы") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Трицепсы"
                        isExpanded = false
                    },
                    text = { Text("Трицепсы") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Предплечья"
                        isExpanded = false
                    },
                    text = { Text("Предплечья") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Средняя часть спины"
                        isExpanded = false
                    },
                    text = { Text("Средняя часть спины") }
                )
                DropdownMenuItem(
                    onClick = {
                        selectedGroup = "Поясница"
                        isExpanded = false
                    },
                    text = { Text("Поясница") }
                )
            }
            Spacer(
                modifier = Modifier
                    .height(25.dp)
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
                    value = youtubelinkInput,
                    maxLines = 1,
                    singleLine = true,
                    onValueChange = {
                        youtubelinkInput = it
                    },
                    decorationBox = { innerTextField ->
                        Column {
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(Modifier.weight(1f)) {
                                    if (youtubelinkInput.length < 1) {
                                        Text(text = "Ссылка с youtube", color = iconGray)
                                    } else {
                                        innerTextField()
                                    }
                                }
                            }
                        }
                    })
            }
            Spacer(modifier = Modifier.height(15.dp))
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
                Text(text = "Наименование", fontSize = 18.sp, color = blue)
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(white)
                        .padding(horizontal = 15.dp, vertical = 10.dp),
                    value = algorhitmInput,
                    maxLines = 150,
                    singleLine = false,
                    onValueChange = {
                        algorhitmInput = it
                    },
                    decorationBox = { innerTextField ->
                        Column {
                            Text(text = "Техника выполнения", fontSize = 18.sp, color = blue)
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(Modifier.weight(1f)) {
                                    if (algorhitmInput.isEmpty()) {
                                        Text(text = "Техника выполнения", color = iconGray)
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
                    if(algorhitmInput.length<6 || nameInput.length< 3){
                        if(algorhitmInput.length>0 || nameInput.length> 0)
                        showMessage = true
                    }else {
                        CoroutineScope(Dispatchers.IO).launch {
                            if (
                                controller.isComplex
                            ) {

                            } else {
                                val newExercise = ExerciseCreation(
                                    nameInput,
                                    algorhitmInput,
                                    null,
                                    youtubelinkInput,
                                    controller.translateGroupName(selectedGroup)
                                )
                                controller.createExercise(newExercise)
                                navHostController.navigate("exerciseList")
                            }
                        }
                    }
                }) {
                Text(text = "Сохранить", fontSize = 15.sp)
            }
            if(showMessage) {
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier.padding(horizontal = 10.dp).fillMaxWidth(),
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
