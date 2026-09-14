package com.example.recipeapp.presentation.welcome

sealed interface WelcomeIntent {
    data object GoToHome : WelcomeIntent
}