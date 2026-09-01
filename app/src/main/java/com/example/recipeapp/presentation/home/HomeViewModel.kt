package com.example.recipeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.model.FoodTypes
import com.example.recipeapp.domain.repsitory.ResponseRepository
import com.example.recipeapp.presentation.home.HomeEffects.NavigateToDetailScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ResponseRepository
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    private val _effects = MutableSharedFlow<HomeEffects>()
    val effects = _effects.asSharedFlow()


    init {
        fetchRecipes()
    }

    fun onIntent(intent: HomeIntent) {

        when (intent) {
            is HomeIntent.ClearError -> {

                _state.update {
                    it.copy(
                        error = null
                    )
                }
            }

            is HomeIntent.SearchUpdate -> {

                val filteredRecipes = if (intent.query.isBlank()) {
                    state.value.allRecipes
                } else {
                    state.value.allRecipes.filter { recipe ->
                        recipe.title.contains(
                            intent.query,
                            ignoreCase = true
                        )
                    }
                }

                _state.value = state.value.copy(
                    searchQueryFlow = intent.query,
                    trendingRecipes = filteredRecipes,
                    isSearching = intent.query.isNotBlank()
                )
            }

            is HomeIntent.SearchRecipe -> {
                getRecipeByName(intent.recipeName)
            }

            is HomeIntent.ItemClick -> {

                val recipe = state.value.allRecipes.firstOrNull {
                    it.id == intent.responseId
                }
                viewModelScope.launch {

                    if (recipe != null) {

                        _effects.emit(
                            NavigateToDetailScreen(recipe)
                        )

                    } else {

                        _state.update {
                            it.copy(
                                error = "Recipe not found"
                            )
                        }
                    }
                }
            }

            is HomeIntent.FoodTypeClicked -> {

                val filteredRecipes = if (intent.foodType == FoodTypes.ALL) {
                    state.value.allRecipes
                } else {
                    state.value.allRecipes.filter { recipe ->
                        recipe.title.contains(
                            intent.foodType.value,
                            ignoreCase = true
                        )
                    }
                }

                _state.update {
                    it.copy(
                        selectedFoodType = intent.foodType,
                        trendingRecipes = filteredRecipes,
                    )
                }
            }
        }
    }
    private fun getRecipeByName(recipeName: String) {
        if (recipeName.isBlank()) return
        val recipe = state.value.allRecipes.firstOrNull {
            it.title.contains(
                recipeName,
                ignoreCase = true
            )
        }
        viewModelScope.launch {
            if (recipe != null) {
                _effects.emit(
                    NavigateToDetailScreen(recipe)
                )
            } else {

                _state.update {
                    it.copy(
                        error = "Recipe not found"
                    )
                }
            }
        }
    }

    private fun fetchRecipes() {

        viewModelScope.launch {

            _state.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            try {

                val responses = repository.responses()
                    ?: throw Exception("No recipes found")

                _state.update {
                    it.copy(
                        allRecipes = responses,
                        trendingRecipes = responses,
                        isLoading = false,
                        error = null
                    )
                }

            } catch (_: Exception) {

                _state.update {
                    it.copy(
                        allRecipes = emptyList(),
                        trendingRecipes = emptyList(),
                        isLoading = false,
                        error = "No Internet Connection"
                    )
                }
            }
        }
    }
}