package com.example.recipeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.recipeapp.presentation.details.DetailIntent
import com.example.recipeapp.presentation.details.DetailScreen
import com.example.recipeapp.presentation.details.DetailViewModel
import com.example.recipeapp.presentation.home.HomeEffects
import com.example.recipeapp.presentation.home.HomeScreen
import com.example.recipeapp.presentation.home.HomeViewModel
import com.example.recipeapp.presentation.welcome.WelcomeEffects
import com.example.recipeapp.presentation.welcome.WelcomeScreen
import com.example.recipeapp.presentation.welcome.WelcomeViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Welcome) {

        composable<Routes.Welcome> {

            val viewModel: WelcomeViewModel = koinViewModel()

            LaunchedEffect(Unit) {
                viewModel.effects.collect { effect ->

                    when (effect) {

                        WelcomeEffects.NavigateToHomeScreen -> {
                            navController.navigate(
                                Routes.Home
                            ) {
                                popUpTo(Routes.Welcome) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
            WelcomeScreen(onIntent = viewModel::onIntent)
        }
        composable<Routes.Home> {

            val viewModel: HomeViewModel = koinViewModel()

            val state by viewModel.state.collectAsStateWithLifecycle()


            LaunchedEffect(Unit) {
                viewModel.effects.collect { effect ->
                    when (effect) {
                        is HomeEffects.NavigateToDetailScreen -> {
                            effect.recipe?.let { recipe ->
                                navController.navigate(
                                    Routes.Detail(
                                        recipeId = recipe.id
                                    )
                                )
                            }
                        }
                    }
                }
            }

            HomeScreen(
                uiState = state,
                onIntent = viewModel::onIntent
            )
        }
        composable<Routes.Detail> { backStackEntry ->

            val route = backStackEntry.toRoute<Routes.Detail>()

            val viewModel: DetailViewModel = koinViewModel()

            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(route.recipeId) {
                viewModel.onIntent(
                    DetailIntent.LoadRecipe(route.recipeId)
                )
            }

            DetailScreen(
                state = state
            )
        }
    }
}

