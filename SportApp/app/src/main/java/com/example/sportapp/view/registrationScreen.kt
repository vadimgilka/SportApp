package com.example.sportapp.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.decode.ImageSource
import com.example.sportapp.R
import com.example.sportapp.ui.theme.bgGray
import com.example.sportapp.view.elements.GifImage

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun registrationScreen(
    onClick: () -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(bgGray),

    ){
        IconButton(
            onClick = {
                onClick()
            }){
            Icon(modifier = Modifier.padding(start = 15.dp),
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = null)
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = "registration!",
            fontSize = 35.sp
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {
            //onClick()
        }) {
            Text(text = "goBack", fontSize = 15.sp)
        }
    }
}