package com.example.sportapp.view

import Model.SportAppApi
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.view.controllers.MainScreenController
import com.example.sportapp.view.elements.catalogue
import com.example.sportapp.view.elements.defaultNavi
import com.example.sportapp.view.elements.exercise
import com.example.sportapp.view.elements.goOutNavBar
import com.example.sportapp.view.elements.leaderboard
import com.example.sportapp.view.elements.list
import com.example.sportapp.view.elements.options
import com.example.sportapp.view.elements.pill
import com.example.sportapp.view.elements.trainStart


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun mainScreen(
    nav: NavHostController,
    api: SportAppApi
)
{
    val contentNav = rememberNavController()
    val controller = MainScreenController(navController = nav, SportAppApi(""))
    var navBarMode by remember {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = { defaultNavi(controller = contentNav)}
    ) {
        screenGraph(nav = contentNav, api = api)
    }

//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Top
//    ) {
//    }
}

@Composable
fun screenGraph(
    nav: NavHostController,
    api: SportAppApi
){
    NavHost(navController = nav, startDestination = "exercise"){
        composable("exercise"){
            trainStart()
        }
        composable("list"){
            catalogue()
        }
        composable("pill"){
            pill()
        }
        composable("leaderboard"){
            leaderboard()
        }
        composable("options"){
            options()
        }
    }
}