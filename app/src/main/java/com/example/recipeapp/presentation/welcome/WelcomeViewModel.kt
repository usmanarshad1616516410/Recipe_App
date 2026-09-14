package com.example.recipeapp.presentation.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class WelcomeViewModel : ViewModel() {

    private val _effects = MutableSharedFlow<WelcomeEffects>()
    val effects = _effects.asSharedFlow()

    fun onIntent(intent: WelcomeIntent) {
        when (intent) {

            WelcomeIntent.GoToHome -> {
                viewModelScope.launch {
                    _effects.emit(
                        WelcomeEffects.NavigateToHomeScreen
                    )
                }
            }
        }
    }
}