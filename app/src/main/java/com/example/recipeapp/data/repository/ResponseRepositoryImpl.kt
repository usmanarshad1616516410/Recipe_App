package com.example.recipeapp.data.repository


import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.toDomainList
import com.example.recipeapp.domain.model.Response
import com.example.recipeapp.domain.repsitory.ResponseRepository

class ResponseRepositoryImpl(
    private val api: RecipeApi,
) : ResponseRepository {
    private var cachedRecipes: List<Response>? = null

    override suspend fun responses(): List<Response> {

        cachedRecipes?.let {
            return it
        }
        val response = api.getRecipe()
        if (!response.isSuccessful) {
            throw Exception("Request failed: ${response.code()}")
        }
        val list = response.body()?.recipes?.toDomainList()
            ?: throw Exception("No recipes found")

        cachedRecipes = list

        return list
    }

    override suspend fun getResponseById(id: String): Response? {
        return cachedRecipes?.find { it.id == id }
    }
}