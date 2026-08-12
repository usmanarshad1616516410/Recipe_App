package com.example.recipeapp.presentation.home

import com.example.recipeapp.domain.model.Meal

sealed interface HomeEffects {
    data class NavigateToDetailScreen(val recipe: Meal?): HomeEffects
}