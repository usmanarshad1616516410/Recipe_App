package com.example.recipeapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    val id: String,
    val name: String,
    val thumbnailUrl: String,
    val ingredients: List<String>,
    val instructions: List<String> = emptyList()
)