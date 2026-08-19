package com.example.recipeapp.domain.repsitory

import com.example.recipeapp.domain.model.Response

interface ResponseRepository {
    suspend fun getMeals(): List<Response>?
    suspend fun getMealById(id: String): Response?
}