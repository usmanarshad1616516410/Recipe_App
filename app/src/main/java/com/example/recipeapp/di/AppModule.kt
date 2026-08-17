package com.example.recipeapp.di

import com.example.recipeapp.data.remote.RecipeApi
import com.example.recipeapp.data.repository.MealRepositoryImpl
import com.example.recipeapp.domain.repsitory.MealRepository
import com.example.recipeapp.presentation.details.DetailViewModel
import com.example.recipeapp.presentation.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    single<RecipeApi> {
        get<Retrofit>().create(RecipeApi::class.java)
    }
    single<MealRepository> { MealRepositoryImpl(get()) }
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        DetailViewModel(get())
    }
}
