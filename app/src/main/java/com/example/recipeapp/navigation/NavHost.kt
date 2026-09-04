package com.example.recipeapp.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
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
fun NavigationStack(
    context: Context
) {

    val backStack = rememberNavBackStack(Routes.Welcome)

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {

            entry<Routes.Welcome> {

                val viewModel: WelcomeViewModel =
                    koinViewModel()

                LaunchedEffect(Unit) {

                    viewModel.effects.collect { effect ->

                        when (effect) {

                            WelcomeEffects.NavigateToHomeScreen -> {

                                backStack.clear()

                                backStack.add(
                                    Routes.Home
                                )
                            }
                        }
                    }
                }

                WelcomeScreen(
                    onIntent = viewModel::onIntent
                )
            }

            entry<Routes.Home> {

                val viewModel: HomeViewModel =
                    koinViewModel()

                val state by viewModel.state
                    .collectAsStateWithLifecycle()

                LaunchedEffect(Unit) {

                    viewModel.effects.collect { effect ->

                        when (effect) {

                            is HomeEffects.NavigateToDetailScreen -> {

                                backStack.add(
                                    Routes.Detail(
                                        recipeId = effect.recipe.id
                                    )
                                )
                            }

                            is HomeEffects.ShowToast -> {
                                Toast.makeText(
                                    context,
                                    effect.message,
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }

                HomeScreen(
                    uiState = state,
                    onIntent = viewModel::onIntent
                )
            }

            entry<Routes.Detail> { key ->

                val viewModel: DetailViewModel =
                    koinViewModel()

                val state by viewModel.state
                    .collectAsStateWithLifecycle()


                LaunchedEffect(key.recipeId) {

                    viewModel.onIntent(
                        DetailIntent.LoadRecipe(
                            key.recipeId
                        )
                    )
                }

                DetailScreen(
                    state = state
                )
            }
        }
    )
}
