package com.example.sportapp.view.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.ui.theme.bgGray


@Composable
fun goOutNavBar(

){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(bgGray),
    ) {
        IconButton(
            onClick = {

            }) {
            Icon(
                modifier = Modifier.padding(start = 15.dp),
                painter = painterResource(id = R.drawable.logout),
                tint = Color.Black,
                contentDescription = null
            )
        }
    }
}

@Composable
fun goBackNavBar(
    onClick: () -> Unit
){
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
                tint = Color.Black,
                contentDescription = null
            )
        }
    }
}


@Composable
fun searchAddNavBar(
    onClick: () -> Unit,
    onAdd: () -> Unit
){
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
                tint = Color.Black,
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.fillMaxWidth(0.65f))
        IconButton(
            onClick = {

            }) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                tint = Color.Black,
                contentDescription = null
            )
        }
        IconButton(
            onClick = {
                onAdd()
            }) {
            Icon(
                painter = painterResource(id = R.drawable.add_circle),
                tint = Color.Black,
                contentDescription = null
            )
        }
    }
}