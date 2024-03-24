package com.example.sportapp.view.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sportapp.R
import com.example.sportapp.ui.theme.bgGray

@Preview
@Composable
fun goOutNavBar(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(bgGray),
    ) {
        IconButton(
            onClick = {

            }) {
            Icon(
                modifier = Modifier.padding(start = 15.dp),
                painter = painterResource(id = R.drawable.logout),
                contentDescription = null
            )
        }
    }
}