package com.example.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.recipeapp.presentation.details.DetailScreen
import com.example.recipeapp.presentation.home.HomeScreen
import com.example.recipeapp.presentation.welcome.WelcomeScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Welcome) {

        composable<Routes.Welcome> {
            WelcomeScreen(
                navController
            )
        }

        composable<Routes.Home> {

            HomeScreen(
                navController
            )

        }
        composable<Routes.Detail> { backStackEntry ->

            val route = backStackEntry.toRoute<Routes.Detail>()

            DetailScreen(
                recipeId = route.recipeId
            )

        }
    }
}


