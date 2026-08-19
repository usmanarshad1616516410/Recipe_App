package com.example.recipeapp.domain.model
data class Response(
    val id: String,
    val name: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: List<String> = emptyList()
)



