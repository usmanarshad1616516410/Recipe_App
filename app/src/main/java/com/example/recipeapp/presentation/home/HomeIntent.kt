package com.example.recipeapp.presentation.home

import com.example.recipeapp.data.remote.Recipe
import retrofit2.http.Query

sealed interface HomeIntent{
     data class Searchupdate(val query: Query): HomeIntent
    data class Foryouitemclick(val recipe: Recipe): HomeIntent
    data class Trendingitem(val recipe: Recipe): HomeIntent
}