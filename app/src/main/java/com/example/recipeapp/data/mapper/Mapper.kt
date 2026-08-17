package com.example.recipeapp.data.mapper

import com.example.recipeapp.domain.model.Meal
import com.example.recipeapp.data.remote.RecipeDto
import com.example.recipeapp.data.remote.RecipeResponse
fun RecipeDto.toDomain(): Meal {
    return Meal(
        id = this.id.toString(),
        name = this.name,
        imageUrl = this.image,
        ingredients =this.ingredients,
        instructions = this.instructions
    )
}

fun RecipeResponse.toDomainList(): List<Meal> {
    return this.recipes.map { recipeDto ->
        recipeDto.toDomain()
    }
}
