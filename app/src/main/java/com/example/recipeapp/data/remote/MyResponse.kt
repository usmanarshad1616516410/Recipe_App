package com.example.recipeapp.data.remote

data class MyResponse(
    val recipes: List<Recipe>,
    val total: Int = 0
)