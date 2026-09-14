package com.example.recipeapp.presentation.home

import com.example.recipeapp.domain.model.Response

sealed interface HomeEffects {
    data class NavigateToDetailScreen(val recipe: Response) : HomeEffects
    data class ShowToast(val message: String) : HomeEffects
}