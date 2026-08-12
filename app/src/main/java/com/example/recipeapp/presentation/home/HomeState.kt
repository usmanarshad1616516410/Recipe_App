package com.example.recipeapp.presentation.home

import com.example.recipeapp.domain.model.Meal

data class HomeState(
    val trendingRecipes: List<Meal> = emptyList(),
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val error: String? = null,
    val searchQueryFlow: String = ""
)