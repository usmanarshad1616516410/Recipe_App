package com.example.recipeapp.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface RecipeApi {
    @GET("recipes")
    suspend fun getRecipe(): Response<RecipeListDto>

//    @GET("recipes/{id}")
//    suspend fun getRecipeById(@Path("id") id: String): Response<RecipeDto>
}
//"https://dummyjson.com/recipes/search"

