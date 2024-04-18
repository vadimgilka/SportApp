package com.example.sportapp.view.elements

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun timerSetUp(
    nav: NavHostController
)
{
    var minuteInput by remember {
        mutableStateOf("")
    }
    Scaffold (topBar = {
        goBackNavBar {
        //nav.navigate("exercise")
    }
    }) {
        Column(modifier = Modifier
            .background(white)
            .fillMaxSize()
            .padding(top = 70.dp), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
            GifImage(modifier = Modifier.size(370.dp), image = R.drawable.timer)
            Spacer(modifier = Modifier.height(20.dp))
            Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
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
                            .padding(horizontal = 15.dp, vertical = 10.dp),
                        value = minuteInput,
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            minuteInput = it
                        },
                        decorationBox = {
                                innerTextField ->
                            Row (
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box (Modifier.weight(1f)) {
                                    if(minuteInput.isEmpty()){
                                        Text(text = "Минуты", color = iconGray)
                                    }else {
                                        innerTextField()
                                    }
                                }
                            }
                        })
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
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
                        value = minuteInput,
                        maxLines = 1,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        onValueChange = {
                            minuteInput = it
                        },
                        decorationBox = {
                                innerTextField ->
                            Row (
                                Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box (Modifier.weight(1f)) {
                                    if(minuteInput.isEmpty()){
                                        Text(text = "Секунды", color = iconGray)
                                    }else {
                                        innerTextField()
                                    }
                                }
                            }
                        })
                }
            }
            Spacer(modifier = Modifier.height(60.dp))
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
                    nav.navigate("trainMenu")
                }) {
                Text(text = "Начать тренировку", fontSize = 15.sp)
            }
        }
    }
}