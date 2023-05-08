package com.practice.test.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practice.test.screens.Home
import com.practice.test.screens.Profile
import com.practice.test.screens.Welcome

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        homeRoute(
            navigateToWelcome = {
                navController.navigate(NavRoutes.Welcome.route)
            },
            navigateToWelcomeWithArgs = { it ->
                navController.navigate(NavRoutes.Welcome.route + "/$it")
            }
        )
        welcomeRoute(
            navigateToProfile = {
                navController.navigate(NavRoutes.Profile.route) {
                    popUpTo(NavRoutes.Home.route)
                }
            }
        )
        profileRoute()
    }
}

fun NavGraphBuilder.homeRoute(
    navigateToWelcome: () -> Unit,
    navigateToWelcomeWithArgs: (String) -> Unit
) {
    composable(route = NavRoutes.Home.route) {
        Home(
            navigateToWelcome = navigateToWelcome,
            navigateToWelcomeWithArgs = navigateToWelcomeWithArgs
        )
    }
}

fun NavGraphBuilder.welcomeRoute(
    navigateToProfile: () -> Unit,
) {
    composable(route = NavRoutes.Welcome.route + "/{userName}") { backStackEntry ->
        Welcome(
            navigateToProfile = navigateToProfile,
            userName = backStackEntry.arguments?.getString("userName")
        )
    }
}

fun NavGraphBuilder.profileRoute() {
    composable(route = NavRoutes.Profile.route) {
        Profile()
    }
}