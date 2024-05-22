package com.example.sportapp.view

import Model.SportAppApi
import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.bgGray
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.errorRed
import com.example.sportapp.ui.theme.green
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.RegistrationScreenController
import com.example.sportapp.view.elements.GifImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun registrationScreen(
    onClick: () -> Unit,
    api: SportAppApi,
    navController: NavHostController,
) {
    var passwordInput by remember {
        mutableStateOf("")
    }
    var loginInput by remember {
        mutableStateOf("")
    }
    var emailInput by remember {
        mutableStateOf("")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var showError by remember {
        mutableStateOf(0f)
    }
    val controller = RegistrationScreenController(api)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(bgGray),
    ) {
        IconButton(
            onClick = {
                onClick()
            }) {
            Icon(
                modifier = Modifier.padding(start = 15.dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null
            )
        }
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 5.dp), horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Регистрация", fontSize = 20.sp)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        GifImage(modifier = Modifier.width(270.dp), image = R.drawable.lottiepodtiag)
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
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
                value = loginInput,
                maxLines = 1,
                singleLine = true,
                onValueChange = {
                    loginInput = it
                },
                decorationBox = { innerTextField ->
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.weight(1f)) {
                            if (loginInput.isEmpty()) {
                                Text(text = "Логин", color = iconGray)
                            } else {
                                innerTextField()
                            }
                        }
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.person),
                            tint = iconGray,
                            contentDescription = null
                        )
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
                .padding(horizontal = 50.dp)
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
                value = passwordInput,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                onValueChange = {
                    passwordInput = it
                },
                decorationBox = { innerTextField ->
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.weight(1f)) {
                            if (passwordInput.isEmpty()) {
                                Text(text = "Пароль", color = iconGray)
                            } else {
                                innerTextField()
                            }
                        }
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.bx_lock_alt),
                            tint = iconGray,
                            contentDescription = null
                        )
                    }
                }
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 50.dp)
        )
        {
            Text(
                text = "Не менее 8 символов.\nЗапрещен ввод «?», «#», «<», «>», «%», «@», «/»",
                fontSize = 11.sp,
                color = blue
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
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
                value = emailInput,
                maxLines = 1,
                singleLine = true,
                onValueChange = {
                    emailInput = it
                },
                decorationBox = { innerTextField ->
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(Modifier.weight(1f)) {
                            if (emailInput.isEmpty()) {
                                Text(text = "Email", color = iconGray)
                            } else {
                                innerTextField()
                            }
                        }
                        Icon(
                            ImageVector.vectorResource(id = R.drawable.doge),
                            tint = iconGray,
                            contentDescription = null
                        )
                    }
                })
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 70.dp)
            .alpha(showError)) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Введены неверные данные", color = errorRed, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(35.dp))
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
            shape = RoundedCornerShape(15.dp), onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val res = controller.onRegister(loginInput, passwordInput, emailInput)
                    Log.e("tag1", res) //"testuser0", "password1234567"
                    if (res.equals("Authorized")) {
                        showDialog = true
                        CoroutineScope(Dispatchers.Main).launch {
                            navController.navigate("mainScreen")
                        }
                    }
                    else
                    showError = 1f
                }
            }) {
            Text(text = "Завершить регистрацию", fontSize = 15.sp)
        }
        if (showDialog == true) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "you have been registrated!") },
                confirmButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text(text = "Закрыть", color = Color.White)
                    }
                })
        }
    }
}