package com.example.recipeapp.data.remote
data class RecipeResponse(
    val recipes: List<RecipeDto>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

data class RecipeDto(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val image: String
)

