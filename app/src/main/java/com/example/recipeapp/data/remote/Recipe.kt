package com.example.recipeapp.data.remote

import com.example.recipeapp.domain.model.Response

data class RecipeDto(
    val id: Int,
    val name: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val image: String
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
        instructions = this.instructions
    )
}

fun List<RecipeDto>.toDomainList(): List<Response> {
    return this.map { it.toDomain() }
}
