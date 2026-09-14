package com.example.recipeapp.presentation.welcome

sealed interface WelcomeEffects {
    data object NavigateToHomeScreen : WelcomeEffects
}