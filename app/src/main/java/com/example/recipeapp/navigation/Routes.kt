package com.example.recipeapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Routes{
    @Serializable
    data object Welcome: Routes
    @Serializable
    data object Home: Routes
    @Serializable
    data class Detail(val recipeId: String): Routes
}