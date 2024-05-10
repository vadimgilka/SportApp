package com.example.sportapp.view

import Model.SportAppApi
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sportapp.models.api.ExerciseListsScreenController
import com.example.sportapp.view.controllers.ComplexListScreenController
import com.example.sportapp.view.controllers.MainScreenController
import com.example.sportapp.view.controllers.UpdateExerciseController
import com.example.sportapp.view.elements.catalogue
import com.example.sportapp.view.elements.complexCreate
import com.example.sportapp.view.elements.complexExercises
import com.example.sportapp.view.elements.complexList
import com.example.sportapp.view.elements.defaultNavi
import com.example.sportapp.view.elements.exerciseList
import com.example.sportapp.view.elements.exerciseView
import com.example.sportapp.view.elements.exertionList
import com.example.sportapp.view.elements.leaderboard
import com.example.sportapp.view.elements.options
import com.example.sportapp.view.elements.pill
import com.example.sportapp.view.elements.timerSetUp
import com.example.sportapp.view.elements.trainMenu
import com.example.sportapp.view.elements.trainOptions
import com.example.sportapp.view.elements.trainStart
import com.example.sportapp.view.elements.updateExercise


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
    val updateExerciseController = UpdateExerciseController(api)
    val complexListScreenController = ComplexListScreenController(api)
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
            complexList(nav, complexListScreenController)
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
            exerciseView(exerciseListsScreenController, nav, updateExerciseController)
        }
        composable("updateExercise"){
            updateExercise(
                nav, updateExerciseController
            )
        }
        composable("complexCreate"){
            complexCreate()
        }
        composable("complexExercises"){
            complexExercises(nav = nav, complexController = complexListScreenController, exerciseController = exerciseListsScreenController)
        }
    }
}