package com.example.recipeapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/search")
    suspend fun getRecipe(
        @Query("q") search : String
    ): Response<MyResponse>
}