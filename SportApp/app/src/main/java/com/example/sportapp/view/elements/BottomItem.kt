package com.example.sportapp.view.elements

import com.example.sportapp.R

sealed class BottomItem(val icon: Int, val route: String) {
    object Exercise: BottomItem(R.drawable.exercise, "exercise");
    object List: BottomItem(R.drawable.reorder, "list");
    object Pill: BottomItem(R.drawable.pill, "pill");
    object LeaderBoard: BottomItem(R.drawable.leaderboard, "leaderboard");
    object Options: BottomItem(R.drawable.bytesize_settings, "options");
}