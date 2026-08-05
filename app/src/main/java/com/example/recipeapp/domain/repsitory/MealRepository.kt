package com.example.recipeapp.domain.repsitory

import com.example.recipeapp.domain.model.Meal

interface MealRepository {
    suspend fun getMeals(): List<Meal>
}