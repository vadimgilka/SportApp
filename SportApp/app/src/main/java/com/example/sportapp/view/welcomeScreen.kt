package com.example.sportapp.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.sportapp.R
import com.example.sportapp.ui.theme.SportAppTheme
import com.example.sportapp.ui.theme.*
import com.example.sportapp.view.elements.GifImage

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun welcomeScreen(
    onRegClick: () -> Unit, onLoginClick: () -> Unit
){
    Column(
        modifier = Modifier.background(Color.White).fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        GifImage(modifier = Modifier.size(320.dp), R.drawable.welcome)
        Image(modifier = Modifier.width(160.dp), imageVector = ImageVector.vectorResource(id = R.drawable.sportapplogo), contentDescription = null)
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 60.dp),
            colors = ButtonColors(blue, white, blue, Color.Transparent),
            shape = RoundedCornerShape(15.dp), onClick = {
            onLoginClick()
        }) {
            Text(text = "Вход", fontSize = 15.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 60.dp), colors = ButtonColors(green, white, green, Color.Transparent), shape = RoundedCornerShape(15.dp), onClick = {
            onRegClick()
        }
        ) {
            Text(text = "Регистрация", fontSize = 15.sp)
        }
    }
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(modifier = Modifier.width(160.dp), imageVector = ImageVector.vectorResource(id = R.drawable.glk), contentDescription = null)
        Spacer(modifier = Modifier.height(20.dp))
    }
}