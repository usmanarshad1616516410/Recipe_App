package com.example.recipeapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes{
    @Serializable
    data object Welcome: Routes
    @Serializable
    data class Home(val userData: String?): Routes
    @Serializable
    data class Detail(val recipe: String?): Routes
}