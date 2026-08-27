package com.example.recipeapp.presentation.home

import com.example.recipeapp.domain.model.FoodTypes
import com.example.recipeapp.domain.model.Response

data class HomeState(
    val allRecipes: List<Response> = emptyList(),
    val trendingRecipes: List<Response> = emptyList(),
    val isLoading: Boolean = false,
    val isSearching: Boolean = false,
    val error: String? = "No Internet Connection",
    val searchQueryFlow: String = "",
    val selectedFoodType: FoodTypes = FoodTypes.ALL
)

