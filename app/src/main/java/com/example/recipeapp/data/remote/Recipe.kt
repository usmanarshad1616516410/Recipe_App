package com.example.recipeapp.data.remote

data class Recipe(
    val id: Int = 0,
    val image: String = "",
    val ingredients: List<String> = emptyList(),
    val instructions: List<String> = emptyList(),
    val name: String = ""
)