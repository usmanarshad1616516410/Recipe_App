package com.example.recipeapp.presentation.home

import com.example.recipeapp.data.remote.Recipe

data class HomeState(
    val searchTitle: String ="",
    val forYouRecipe: Recipe,
    val trendingRecipes: List<Recipe>
)