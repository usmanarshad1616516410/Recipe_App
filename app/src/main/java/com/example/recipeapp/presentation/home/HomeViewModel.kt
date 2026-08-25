package com.example.recipeapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                    _effects.emit(
                        NavigateToDetailScreen(recipe)
                    )
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
            _effects.emit(
                HomeEffects.NavigateToDetailScreen(recipe)
            )
        }
    }

    private fun fetchRecipes() {

        viewModelScope.launch {

            _state.value = state.value.copy(
                isLoading = true,
                error = null
            )

            try {

                val responses = repository.responses().orEmpty()

                _state.update {
                    it.copy(
                        allRecipes = responses,
                        trendingRecipes = responses,
                        isLoading = false,
                        error = null
                    )
                }

            } catch (e: Exception) {

                _state.value = state.value.copy(
                    isLoading = false,
                    error = e.message ?: "No Internet Connection"
                )
            }
        }
    }
}