package com.example.recipeapp.data.repository


import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.remote.toDomain
import com.example.recipeapp.data.remote.toDomainList
import com.example.recipeapp.domain.model.Response
import com.example.recipeapp.domain.repsitory.ResponseRepository

class ResponseRepositoryImpl(
    private val api: RecipeApi,
) : ResponseRepository {

    private var cachedRecipes: List<Response>? = null

    override suspend fun responses(): List<Response>? {
        return cachedRecipes ?: run {
            val response = api.getRecipe()
            val list = response.body()?.recipes?.toDomainList()
            cachedRecipes = list
            list
        }
    }

    override suspend fun getResponseById(id: String): Response? {
        return cachedRecipes?.find { it.id == id }

//        val response = api.getRecipeById(id)
//        return response.body()?.toDomain()
    }

}