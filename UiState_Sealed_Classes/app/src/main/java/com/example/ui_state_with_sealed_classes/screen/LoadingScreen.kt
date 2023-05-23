package com.example.ui_state_with_sealed_classes.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen(

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.weight(.2f))
        Text("Loading Screen")
        Spacer(modifier = Modifier.weight(.3f))
        CircularProgressIndicator(
            color = Color.Gray,
            modifier = Modifier
                .size(50.dp)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.weight(.5f))
    }
}