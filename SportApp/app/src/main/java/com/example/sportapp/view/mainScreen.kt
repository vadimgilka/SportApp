package com.example.sportapp.view

import Model.SportAppApi
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.sportapp.view.controllers.ExerciseListsScreenController
import com.example.sportapp.view.controllers.MainScreenController
import com.example.sportapp.view.elements.catalogue
import com.example.sportapp.view.elements.complexList
import com.example.sportapp.view.elements.defaultNavi
import com.example.sportapp.view.elements.exercise
import com.example.sportapp.view.elements.exerciseList
import com.example.sportapp.view.elements.exerciseView
import com.example.sportapp.view.elements.exertionList
import com.example.sportapp.view.elements.goOutNavBar
import com.example.sportapp.view.elements.leaderboard
import com.example.sportapp.view.elements.list
import com.example.sportapp.view.elements.options
import com.example.sportapp.view.elements.pill
import com.example.sportapp.view.elements.timerSetUp
import com.example.sportapp.view.elements.trainMenu
import com.example.sportapp.view.elements.trainOptions
import com.example.sportapp.view.elements.trainStart


@RequiresApi(Build.VERSION_CODES.P)
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
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun screenGraph(
    nav: NavHostController,
    api: SportAppApi
){
    val exerciseListsScreenController = ExerciseListsScreenController(api)
    NavHost(navController = nav, startDestination = "exercise"){
        composable("exercise"){
            trainStart(nav)
        }
        composable("list"){
            catalogue(nav)
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
        composable("exerciseOptions"){
            trainOptions(nav)
        }
        composable("complexList"){
            complexList(nav)
        }
        composable("exerciseList"){
            exerciseList(nav, exerciseListsScreenController)
        }
        composable("exertionList"){
            exertionList(nav, exerciseListsScreenController)
        }
        composable("timerSetUp"){
            timerSetUp(
                nav
            )
        }
        composable("trainMenu"){
            trainMenu(
                nav
            )
        }
        composable("exerciseView"){
            exerciseView()
        }
    }
}