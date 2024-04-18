package com.example.sportapp.view.elements

import com.example.sportapp.R

sealed class BottomItem(val icon: Int, val route: String, val children: kotlin.collections.List<String>) {
    object Exercise: BottomItem(R.drawable.exercise, "exercise", listOf("exerciseOptions"));
    object List: BottomItem(R.drawable.reorder, "list", listOf("complexList"));
    object Pill: BottomItem(R.drawable.pill, "pill", listOf());
    object LeaderBoard: BottomItem(R.drawable.leaderboard, "leaderboard", listOf());
    object Options: BottomItem(R.drawable.bytesize_settings, "options", listOf());

}