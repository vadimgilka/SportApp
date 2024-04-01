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
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
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


@Composable
fun defaultNavi(controller: NavHostController) {
    val listItems = listOf(
        BottomItem.Exercise,
        BottomItem.List,
        BottomItem.Pill,
        BottomItem.LeaderBoard,
        BottomItem.Options
    )
    val backStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
        Box() {
            emptyNavi()
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                listItems.forEach { item ->
                    IconButton(onClick = {
                        controller.navigate(item.route)
                    })
                    {
                        Icon(
                            modifier = Modifier.padding(start = 15.dp),
                            painter = painterResource(id = item.icon),
                            tint = if (currentRoute == item.route) blue else Color.Black,
                            contentDescription = null
                        )
                    }
                }
            }

        }
    }
}

