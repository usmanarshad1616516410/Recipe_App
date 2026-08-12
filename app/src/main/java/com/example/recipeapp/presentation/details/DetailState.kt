package com.example.recipeapp.presentation.details

import com.example.recipeapp.domain.model.Meal

data class DetailState(
    val meal: Meal? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedIngredientIndex: Int? = null
)