package com.example.recipeapp.presentation.home

import com.example.recipeapp.domain.model.FoodTypes

sealed interface HomeIntent {
    data class SearchUpdate(val query: String) : HomeIntent
    data class FoodTypeClicked(val foodType: FoodTypes): HomeIntent
    data class SearchRecipe(val recipeName: String) : HomeIntent
    data class ItemClick(val responseId: String) : HomeIntent
    data object ClearError : HomeIntent
}
