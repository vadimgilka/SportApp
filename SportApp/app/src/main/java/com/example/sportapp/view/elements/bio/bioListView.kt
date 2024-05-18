package com.example.sportapp.view.elements.bio

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.R
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.elements.goBackNavBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Preview
fun bioListView(){
    Scaffold(
        topBar = { goBackNavBar {
            //nav.navigate("exercise")
        } }
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
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                for (i in  1..15) {
                    items(1) {
                        bioListItem(
                            true,
                            "Таблетосы",
                            1,
                            {},
                            {}
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                item{
                    Spacer(modifier = Modifier.height(35.dp))
                }
            }
        }
        Column( Modifier
            .fillMaxHeight(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 60.dp),
                colors = ButtonColors(blue, white, blue, Color.Transparent),
                shape = RoundedCornerShape(15.dp), onClick = {

                }) {
                Text(text = "Добавить напоминание", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(65.dp))
        }
    }
}

@Composable
fun bioListItem(
    singlePill: Boolean,
    name: String,
    countPerDay: Int,
    onEditPressed: () -> Unit,
    onDeletePressed: () -> Unit
){
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
    )
    {
        Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start,){
            Column(
                Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
            ) {
                Row{
                    Icon(
                        modifier = Modifier
                            .width(34.dp)
                            .padding(end = 10.dp),
                        painter = painterResource(id = if(singlePill){R.drawable.singlepill}else{R.drawable.pills}),
                        tint = Color.Black,
                        contentDescription = null
                    )
                    Text(text = name, fontSize = 18.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = countPerDay.toString().plus(" раз в день"), color = Color.Black)
                Spacer(modifier = Modifier.height(3.dp))
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top
            ) {
                IconButton(
                    onClick = {
                        onEditPressed
                    }) {
                    Icon(
                        modifier = Modifier.width(20.dp),
                        painter = painterResource(id = R.drawable.penciledit),
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {
                        onDeletePressed
                    }) {
                    Icon(
                        modifier = Modifier.width(20.dp),
                        painter = painterResource(id = R.drawable.rubish),
                        tint = Color.Black,
                        contentDescription = null
                    )
                }
            }
        }
    }
}