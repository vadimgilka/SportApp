package com.example.sportapp.view.elements.body_reaction

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sportapp.models.DTO.bodyreaction.BodyReactionInfo
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.bio.BodyReactionController
import com.example.sportapp.view.elements.goBackNavBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun reactionListView(nav: NavController, controller: BodyReactionController) {

    val elementslist = remember {
        mutableStateListOf(listOf<BodyReactionInfo>())
    }

    CoroutineScope(Dispatchers.IO).launch {
        controller.getMany()?.let {
            elementslist[0] = it
        }
    }

    Scaffold(
        topBar = {
            goBackNavBar {
                nav.navigate("pill")
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
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                for (reaction in elementslist[0]) {

                    item {
                        reactionElement(reaction, nav, controller)
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }
            }
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
                controller.setBodyReaction(BodyReactionInfo(id = -1, "", "", null))
                nav.navigate("reactionInfoView")
            }) {
            Text(text = "Добавить заметку", fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(65.dp))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun reactionElement(reaction: BodyReactionInfo, nav: NavController, controller: BodyReactionController) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .border(1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = {
                controller.setBodyReaction(reaction)
                nav.navigate("reactionInfoView")
            })
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.Start) {
            Column(
                Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp),
            ) {
                Row {
                    Text(text = reaction.name, fontSize = 18.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = truncateString(reaction.detail, 30), fontSize = 15.sp, color = Color.Black)
            }
        }
    }
}


fun truncateString(input: String, maxLength: Int): String {
    return if (input.length > maxLength) {
        input.substring(0, maxLength - 3).plus("...")
    } else {
        input
    }
}