package com.example.sportapp.view.elements.body_reaction

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionInfo
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.bio.BodyReactionController
import com.example.sportapp.view.elements.goBackNavBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun reactionInfoView(nav: NavController, controller: BodyReactionController) {

    var name by remember {
        mutableStateOf(controller.getName())
    }

    var detail by remember {
        mutableStateOf(controller.getDetail())
    }

    Scaffold(
        topBar = {
            goBackNavBar {
                nav.navigate("reactionListView")
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
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp)
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .clickable(onClick = { nav.navigate("reactionInfoView") }),
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
                        controller.setName(it)
                    },
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.weight(1f)) {
                                if (name.isEmpty()) {
                                    Text(text = "Заголовок", color = iconGray)
                                } else {
                                    innerTextField()
                                }
                            }
                        }
                    })
            }
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 5.dp)
                    .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                    .clip(RoundedCornerShape(10.dp))
                    .heightIn(min = 700.dp)
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(white)
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    value = detail,
                    onValueChange = {
                        detail = it
                        controller.setDetail(it)
                    },
                    decorationBox = { innerTextField ->
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(Modifier.weight(1f)) {
                                if (name.isEmpty()) {
                                    Text(text = "Описание", color = iconGray)
                                } else {
                                    innerTextField()
                                }
                            }
                        }
                    })
            }
            Spacer(modifier = Modifier.height(25.dp))
        }
    }

    Column(
        Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 60.dp),
            colors = ButtonColors(blue, white, blue, Color.Transparent),
            shape = RoundedCornerShape(15.dp), onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    controller.makeOperation()
                }
                nav.navigate("reactionListView")
            }) {
            Text(text = "Сохранить", fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(65.dp))
    }
}