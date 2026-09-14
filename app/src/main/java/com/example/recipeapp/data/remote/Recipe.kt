package com.example.recipeapp.data.remote

import com.example.recipeapp.domain.model.Response

data class RecipeDto(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val image: String,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val cuisine: String,
    val rating: Double,
    val tags: List<String>,
    val mealType: List<String>
)
data class RecipeListDto(
    val recipes: List<RecipeDto>
)
fun RecipeDto.toDomain(): Response {
    return Response(
        id = this.id.toString(),
        title = this.name,
        imageUrl = this.image,
        ingredients = this.ingredients,
        instructions = this.instructions,
        prepTimeMinutes = this.prepTimeMinutes,
        cookTimeMinutes = this.cookTimeMinutes,
        cuisine = this.cuisine,
        rating = this.rating,
        tags = tags,
        mealType = mealType


    )
}

fun List<RecipeDto>.toDomainList(): List<Response> {
    return this.map { it.toDomain() }
}
