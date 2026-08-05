package com.example.recipeapp.presentation.home

import androidx.lifecycle.ViewModel
import com.example.recipeapp.data.remote.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    val dummyRecipeForYou = Recipe(
        id = 1,
        image = "https://cdn.dummyjson.com/recipe-images/1.webp", //  image link
        name = "Neapolitan Margherita Pizza",
        ingredients = listOf(
            "Pizza Dough",
            "San Marzano Tomatoes",
            "Fresh Mozzarella",
            "Basil Leaves",
            "Olive Oil"
        ),
        instructions = listOf(
            "Roll out dough",
            "Spread tomato sauce",
            "Add mozzarella and basil",
            "Bake at high heat"
        )
    )
    val dummyTrendingList = listOf(
        Recipe(
            id = 2,
            name = "Classic Lasagna",
            image = "https://cdn.dummyjson.com/recipe-images/1.webp",
            ingredients = listOf(
                "Pizza Dough",
                "San Marzano Tomatoes",
                "Fresh Mozzarella",
                "Basil Leaves",
                "Olive Oil"
            ),
            instructions = listOf(
                "Roll out dough",
                "Spread tomato sauce",
                "Add mozzarella and basil",
                "Bake at high heat"
            )
        ),
        Recipe(
            id = 3,
            name = "Chocolate Brownie",
            image = "https://cdn.dummyjson.com/recipe-images/1.webp",
            ingredients = listOf(
                "Pizza Dough",
                "San Marzano Tomatoes",
                "Fresh Mozzarella",
                "Basil Leaves",
                "Olive Oil"
            ),
            instructions = listOf(
                "Roll out dough",
                "Spread tomato sauce",
                "Add mozzarella and basil",
                "Bake at high heat"
            )
        ),
        Recipe(
            id = 4,
            name = "Chicken Caesar Salad",
            image = "https://cdn.dummyjson.com/recipe-images/1.webp",
            ingredients = listOf(
                "Pizza Dough",
                "San Marzano Tomatoes",
                "Fresh Mozzarella",
                "Basil Leaves",
                "Olive Oil"
            ),
            instructions = listOf(
                "Roll out dough",
                "Spread tomato sauce",
                "Add mozzarella and basil",
                "Bake at high heat"
            )
        )
    )


    private val _state = MutableStateFlow(
        HomeState(
            searchTitle = "",
            forYouRecipe = dummyRecipeForYou,
            trendingRecipes = dummyTrendingList
        )
    )
    val state = _state.asStateFlow()

}