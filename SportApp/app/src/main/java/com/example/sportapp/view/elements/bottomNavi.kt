package com.example.sportapp.view.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sportapp.R
import com.example.sportapp.ui.theme.bgGray
import com.example.sportapp.ui.theme.blue


@Composable
fun emptyNavi() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(bgGray),
        verticalAlignment = Alignment.Bottom
    ) {

    }
}

@Preview
@Composable
fun defaultNavi(){
    var enabledButton by remember {
        mutableStateOf(0)
    }
    Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom){
        Box() {
            emptyNavi()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                IconButton(onClick = { enabledButton = 0/*TODO*/ })
                {
                    Icon(
                        modifier = Modifier.padding(start = 15.dp),
                        painter = painterResource(id = R.drawable.exercise),
                        tint = if(enabledButton == 0) blue else Color.Black,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { enabledButton = 1 /*TODO*/ })
                {
                    Icon(
                        modifier = Modifier.padding(start = 15.dp),
                        painter = painterResource(id = R.drawable.reorder),
                        tint = if(enabledButton == 1) blue else Color.Black,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { enabledButton = 2 /*TODO*/ })
                {
                    Icon(
                        modifier = Modifier.padding(start = 15.dp),
                        painter = painterResource(id = R.drawable.pill),
                        tint = if(enabledButton == 2) blue else Color.Black,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { enabledButton = 3 /*TODO*/ })
                {
                    Icon(
                        modifier = Modifier.padding(start = 15.dp),
                        painter = painterResource(id = R.drawable.leaderboard),
                        tint = if(enabledButton == 3) blue else Color.Black,
                        contentDescription = null
                    )
                }
                IconButton(onClick = { enabledButton = 4 /*TODO*/ })
                {
                    Icon(
                        modifier = Modifier.padding(start = 15.dp),
                        painter = painterResource(id = R.drawable.bytesize_settings),
                        tint = if(enabledButton == 4) blue else Color.Black,
                        contentDescription = null
                    )
                }
            }
        }
    }
}