package com.example.sportapp.view

import LoginScreenController
import Model.SportAppApi
import android.os.Build
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.bgGray
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.errorRed
import com.example.sportapp.ui.theme.iconGray
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.elements.GifImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun loginScreen(nav: NavHostController,onClick: () -> Unit, api: SportAppApi) {
    var passwordInput by remember {
        mutableStateOf("")
    }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var showError by remember {
        mutableStateOf(0f)
    }
    var loginInput by remember {
        mutableStateOf("")
    }
    var controller = LoginScreenController(api)
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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        GifImage(modifier = Modifier.size(320.dp), R.drawable.bicepcurl)
        Spacer(modifier = Modifier.height(30.dp))
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
                decorationBox = {
                    innerTextField ->
                    Row (
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box (Modifier.weight(1f)) {
                            if(loginInput.isEmpty()){
                                Text(text = "Логин", color = iconGray)
                            }else {
                                innerTextField()
                            }
                        }
                        Icon(ImageVector.vectorResource(id = R.drawable.person), tint = iconGray, contentDescription = null)
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
                        Icon(ImageVector.vectorResource(id = R.drawable.bx_lock_alt), tint = iconGray, contentDescription = null)
                    }
                }
            )
        }
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 65.dp).alpha(showError)) {
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = "Введен неверный логин или пароль", color = errorRed, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(10.dp))
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
                CoroutineScope(Dispatchers.IO).launch{
                    val res = controller.onLogin(loginInput, passwordInput)
                    Log.e("tag1", res) //"testuser0", "password1234567"
                    if(res.equals("Authorized"))
                        showDialog = true
                    else
                        showError = 1f
                }
                //onClick()
            }) {
            Text(text = "Войти", fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            colors = ButtonColors(Color.Transparent, blue, Color.Transparent, Color.Transparent),
            onClick = { nav.navigate("mainScreen") }) {
            Text(text = "Забыли пароль?", fontSize = 20.sp)
        }
        if(showDialog == true) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "you have been authorized!") },
                confirmButton = { TextButton(onClick = { showDialog = false}) {
                    Text(text = "Закрыть", color = Color.White)
                } })
        }
    }
}