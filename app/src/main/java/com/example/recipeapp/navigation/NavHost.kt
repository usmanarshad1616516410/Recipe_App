package com.example.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.presentation.details.DetailScreen
import com.example.recipeapp.presentation.home.HomeScreen
import com.example.recipeapp.presentation.welcome.WelcomeScreen

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Welcome) {

        composable<Routes.Welcome> {
            WelcomeScreen(navController = navController)
        }

        composable<Routes.Home> {
            HomeScreen(navController = navController)
        }

        composable<Routes.Detail> {
            DetailScreen(
                navController = navController
            )
        }
    }
}

