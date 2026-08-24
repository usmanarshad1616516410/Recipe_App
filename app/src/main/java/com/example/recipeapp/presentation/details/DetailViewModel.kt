package com.example.recipeapp.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.domain.repsitory.ResponseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: ResponseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadRecipe -> loadRecipe(intent.id)
            is DetailIntent.IngredientClick -> onIngredientClick(intent.index)
        }
    }
    private fun loadRecipe(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val response = repository.getResponseById(id)
                _state.update { it.copy(response = response, isLoading = false) }
            } catch (e: Exception) {
                val message = e.message ?: "Something went wrong"
                _state.update {
                    it.copy(isLoading = false, error = message)
                }
            }
        }
    }
    private fun onIngredientClick(index: Int) {
        _state.update { current ->
            current.copy(
                selectedIngredientIndex = if (current.selectedIngredientIndex == index) null else index
            )
        }
    }
}

