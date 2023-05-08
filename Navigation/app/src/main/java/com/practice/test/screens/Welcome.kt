package com.practice.test.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.test.component.CustomTextField
import com.practice.test.navigation.NavRoutes

@Composable
fun Welcome(
    navigateToProfile: () -> Unit,
    userName: String? = ""
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome, $userName",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.size(30.dp))
            Button(
                onClick ={
                    navigateToProfile()
                }
            ) {
                Text(text = "Set up your Profile")
            }
        }
    }
}

