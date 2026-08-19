package com.example.recipeapp.presentation.details

import com.example.recipeapp.domain.model.Response

data class DetailState(
    val meal: Response? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedIngredientIndex: Int? = null
)