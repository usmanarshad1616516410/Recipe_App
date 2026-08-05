package com.example.recipeapp.data.mapper

import com.example.recipeapp.data.remote.Recipe
import com.example.recipeapp.domain.model.Meal
fun Recipe.toDomain(): Meal {
    return Meal(
        id = this.id.toString(),
        name = this.name,
        thumbnailUrl = this.image
    )
}
fun List<Recipe>.toDomainList(): List<Meal> {
    return this.map { it.toDomain() }
}