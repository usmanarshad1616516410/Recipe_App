package com.example.recipeapp.presentation.details

sealed interface DetailIntent {
    data class LoadRecipe(val id: String) : DetailIntent
    data class IngredientClick(val index: Int) : DetailIntent
}