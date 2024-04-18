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
}