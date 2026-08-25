package com.example.recipeapp.domain.model
data class Response(
    val id: String,
    val title: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val rating: Double,
    val tags: List<String>,
    val mealType: List<String>
)



