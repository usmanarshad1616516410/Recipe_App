package com.example.recipeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.repsitory.MealRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class HomeViewModel(
    private val repository: MealRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private val _effects = MutableSharedFlow<HomeEffects>()
    val effects = _effects.asSharedFlow()


    fun onIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.SearchUpdate -> {
                _state.update {
                    it.copy(searchQueryFlow = intent.query)
                }
            }

            is HomeIntent.SearchRecipe -> {
                getMealByName(intent.recipeName)
            }

            is HomeIntent.ItemClick -> {}
        }
    }

    init {
        loadMeals()
    }
    private fun getMealByName(recipeName: String) {
        val recipe = recipeName.lowercase()
        val searchedRecipe = state.value.trendingRecipes.firstOrNull { meal ->
            meal.name.lowercase() == recipe
        }

        viewModelScope.launch {
            _effects.emit(HomeEffects.NavigateToDetailScreen(searchedRecipe))
        }

    }

    private fun loadMeals() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true, error = null)
            }
            try {
                val meals = repository.getMeals()

                _state.update { it.copy(trendingRecipes = meals ?: emptyList(), isLoading = false) }
            } catch (e: Exception) {
                _state.update {
                    it.copy(isLoading = false, error = e.message ?: "Something went wrong")
                }
            }
        }
    }
}