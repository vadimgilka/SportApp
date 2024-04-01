package com.example.sportapp.view.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun exercise() {
    Text(modifier = Modifier.fillMaxSize(), text = "exercise", textAlign = TextAlign.Center)
}

@Composable
fun list() {
    Text(modifier = Modifier.fillMaxSize(), text = "list", textAlign = TextAlign.Center)
}

@Composable
fun pill() {
    Text(modifier = Modifier.fillMaxSize(), text = "pill", textAlign = TextAlign.Center)
}

@Composable
fun leaderboard() {
    Text(modifier = Modifier.fillMaxSize(), text = "leaderboard", textAlign = TextAlign.Center)
}

@Composable
fun options() {
    Text(modifier = Modifier.fillMaxSize(), text = "options", textAlign = TextAlign.Center)
}