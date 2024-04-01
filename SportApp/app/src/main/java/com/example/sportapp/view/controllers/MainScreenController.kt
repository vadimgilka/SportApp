package com.example.sportapp.view.controllers

import Model.SportAppApi
import androidx.navigation.NavHostController

class MainScreenController {
    private var navController: NavHostController
    private var api: SportAppApi


    constructor(navController: NavHostController, api: SportAppApi) {
        this.navController = navController
        this.api = api
    }

    public fun navBarControl(id: Int){
        when (id){
            0-> navController.navigate("trainStart")
            1-> navController.navigate("catalogue")
            else -> {

            }
        }
    }
}