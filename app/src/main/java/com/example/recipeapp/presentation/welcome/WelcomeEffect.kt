package com.example.recipeapp.presentation.welcome

sealed class Rotes(val route: String) {
    object WelcomeScreen : Rotes("welcome_screen")
}



