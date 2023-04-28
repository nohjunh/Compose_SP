package com.example.readerapp.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.readerapp.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Reader_App",
                navController = navController
            )
        },
        floatingActionButton = {
            FABContent{
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        }
    ) {
        Surface (
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            //HomeContent(navController, viewModel)
        }
    }
}

@Composable
fun ReaderAppBar(
    title: String,
    icon: ImageVector? = null,
    showProfile: Boolean = true,
    navController: NavController,
    onBackArrowClicked:() -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showProfile) {
                    Icon(imageVector = Icons.Default.Favorite,
                    contentDescription = "Logo",
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .scale(0.9f)
                )

            }
            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = "ArrowBack",
                    tint = Color.Red.copy(alpha = 0.7f),
                    modifier = Modifier
                        .clickable {
                            onBackArrowClicked.invoke()
                        }
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            Text(
                text = title,
                color = Color.Red.copy(alpha = 0.7f),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
        }
    },
        actions = {
            IconButton(
                onClick = {
                    FirebaseAuth.getInstance()
                        .signOut().run {
                            navController.navigate(ReaderScreens.LoginScreen.name)
                        }
                }
            ) {
                if (showProfile) {
                    Row() {
                        Icon(
                            imageVector = Icons.Filled.Logout,
                            contentDescription = "Logout",
                        )
                    }
                }else Box {}
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp)
}

@Composable
fun FABContent(
    onTap: () -> Unit
) {
    FloatingActionButton(
        onClick = {onTap()},
        shape = RoundedCornerShape(50.dp),
        backgroundColor = Color(0xFF92CBDF)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a Book",
            tint = Color.White
        )
    }
}