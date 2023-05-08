package com.practice.test.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone

object BottomNavBarItems {
    val BottomBarItems = listOf(
        BottomBarItem(
            title = "Home",
            image = Icons.Filled.Home,
            route = NavRoutes.Home.route
        ),
        BottomBarItem(
            title = "Welcome",
            image = Icons.Filled.Face,
            route = NavRoutes.Welcome.route
        ),
        BottomBarItem(
            title = "Profile",
            image = Icons.Filled.Person,
            route = NavRoutes.Profile.route
        ),
    )
}