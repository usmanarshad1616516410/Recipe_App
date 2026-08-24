package com.example.recipeapp.domain.repsitory

import com.example.recipeapp.domain.model.Response

interface ResponseRepository {
    suspend fun responses(): List<Response>?
    suspend fun getResponseById(id: String): Response?

}