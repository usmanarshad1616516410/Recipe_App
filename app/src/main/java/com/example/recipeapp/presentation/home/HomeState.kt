package com.example.recipeapp.presentation.home

import com.example.recipeapp.domain.model.Response

data class HomeState(
    val allRecipes: List<Response> = emptyList(),
    val trendingRecipes: List<Response> = emptyList(),
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val error: String? = null,
    val searchQueryFlow: String = ""
)