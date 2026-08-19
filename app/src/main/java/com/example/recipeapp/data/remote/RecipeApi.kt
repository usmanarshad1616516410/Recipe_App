package com.example.recipeapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApi {
    @GET("recipes/search")
    suspend fun getRecipe(): Response<RecipeListDto>

    @GET("recipes/{id}")
    suspend fun getRecipeById(@Path("id") id: String): Response<RecipeDto>
}


