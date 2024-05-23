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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sportapp.R
import com.example.sportapp.models.DTO.bio.BioAdditiveInfo
import com.example.sportapp.models.DTO.bio.BioAdditiveUpdation
import com.example.sportapp.ui.theme.blue
import com.example.sportapp.ui.theme.white
import com.example.sportapp.view.controllers.bio.BioAdditiveController
import com.example.sportapp.view.elements.goBackNavBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun bioListView(nav: NavHostController, controller : BioAdditiveController){

    val elementslist = remember {
        mutableStateListOf(listOf<BioAdditiveInfo>())
    }

    CoroutineScope(Dispatchers.IO).launch {
        elementslist[0] = controller.getMany()!!
    }

    Scaffold(
        topBar = { goBackNavBar {
            nav.navigate("exercise")
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
                for ( element in  elementslist[0]) {
                    item{
                        bioListItem(
                            true,
                            element.name,
                            element.reminds.size,
                            //в лямбдах пишешь подгрухку данных по выбранному элементу через котнроллер
                            {nav.navigate("bioUpdateCreateView")},
                            {nav.navigate("bioUpdateCreateView")},
                            controller,
                            element
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
                    controller.operation = BioAdditiveController.Operation.CREATE;
                    nav.navigate("bioAdd")
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
    onDeletePressed: () -> Unit,
    controller : BioAdditiveController,
    bio: BioAdditiveInfo
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
                        controller.operation = BioAdditiveController.Operation.UPDATE;
                        controller.bioAdditiveInfo = bio;
                        onEditPressed()
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
                        controller.operation = BioAdditiveController.Operation.DELETE;
                        controller.bioAdditiveInfo = bio;
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