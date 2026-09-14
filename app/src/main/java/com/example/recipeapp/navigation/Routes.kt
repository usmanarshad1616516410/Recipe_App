package com.example.recipeapp.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
@Serializable
sealed interface Routes : NavKey {
    @Serializable
    data object Welcome: Routes
    @Serializable
    data object Home: Routes
    @Serializable
    data class Detail(val recipeId: String): Routes
}