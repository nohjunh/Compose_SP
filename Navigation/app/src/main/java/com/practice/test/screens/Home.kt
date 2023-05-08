package com.practice.test.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.practice.test.component.CustomTextField

@Composable
fun Home(
    navigateToWelcomeWithArgs: (String) -> Unit
) {
    var userName by remember {
        mutableStateOf("")
    }
    val onUserNameChange = { text: String ->
        userName = text
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomTextField(
                title = "Enter your name",
                textState = userName,
                onTextChange = onUserNameChange
            )
            Spacer(modifier = Modifier.size(30.dp))

            Button(
                onClick = {
                    navigateToWelcomeWithArgs(userName)
                }
            ) {
                Text(text = "Register")
            }
        }
    }
}

