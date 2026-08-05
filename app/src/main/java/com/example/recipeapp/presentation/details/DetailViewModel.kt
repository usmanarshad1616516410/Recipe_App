package com.example.recipeapp.presentation.details

import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.remote.Recipe

class DetailViewModel : ViewModel() {
    val ingredientList = Recipe(
        ingredients = listOf(
            "Pizza Dough",
            "San Marzano Tomatoes",
            "Fresh Mozzarella",
            "Basil Leaves",
            "Olive Oil",
            "Roll out dough",
            "Spread tomato sauce",
            "Add mozzarella and basil",
            "Bake at high heat"
        )
    )
}