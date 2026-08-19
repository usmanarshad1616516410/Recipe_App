package com.example.recipeapp.data.repository


import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.toDomain
import com.example.recipeapp.data.remote.toDomainList
import com.example.recipeapp.domain.model.Response
import com.example.recipeapp.domain.repsitory.ResponseRepository
class ResponseRepositoryImpl(
    private val api: RecipeApi,
) : ResponseRepository {

    override suspend fun getMeals(): List<Response>? {
        val response = api.getRecipe()
        return response.body()?.recipes?.toDomainList()
    }

    override suspend fun getMealById(id: String): Response? {
        val response = api.getRecipeById(id)
        return response.body()?.toDomain()
    }
}