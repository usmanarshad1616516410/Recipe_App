package com.example.recipeapp.data.repository

import com.example.recipeapp.data.mapper.toDomain
import com.example.recipeapp.data.mapper.toDomainList
import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.domain.model.Meal
import com.example.recipeapp.domain.repsitory.MealRepository

class MealRepositoryImpl(
    private val api: RecipeApi
) : MealRepository {

    override suspend fun getMeals(): List<Meal>? {
        val response = api.getRecipe()
        return response.body()?.toDomainList()
    }

    override suspend fun getMealById(id: String): Meal? {
        val response = api.getRecipeById(id)
        return response.body()?.toDomain()
    }
}