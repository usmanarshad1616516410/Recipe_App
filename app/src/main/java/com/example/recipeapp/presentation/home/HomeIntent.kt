package com.example.recipeapp.presentation.home

sealed interface HomeIntent {
    data class SearchUpdate(val query: String) : HomeIntent
    data class SearchRecipe(val recipeName: String) : HomeIntent
    data class ItemClick(val mealId: String) : HomeIntent
}
