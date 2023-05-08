package com.practice.test.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
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
            // 선택적 매개변수는 URL이 "/arg1=$value1/arg2=$value2" 형식이 아닌 "?arg1=$value1&arg2=$value2" 형식을 사용하는 경우에만 작동
            navigateToWelcomeWithArgs = { it ->
                navController.navigate("${NavRoutes.Welcome.route}?userName=$it")
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
            navigateToWelcomeWithArgs = navigateToWelcomeWithArgs
        )
    }
}

fun NavGraphBuilder.welcomeRoute(
    navigateToProfile: () -> Unit,
) {
    // 선택적 매개변수는 URL이 "/arg1=$value1/arg2=$value2" 형식이 아닌 "?arg1=$value1&arg2=$value2" 형식을 사용하는 경우에만 작동
    composable(
        route = "${NavRoutes.Welcome.route}?userName={userName}",
        arguments = listOf(navArgument("userName") {
            type = NavType.StringType
            defaultValue = ""
        })
    ) { backStackEntry ->
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

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    BottomNavigation {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        BottomNavBarItems.BottomBarItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        /*
                            popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        */
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = {
                    Text(text = navItem.title)
                }
            )
        }
    }
}
