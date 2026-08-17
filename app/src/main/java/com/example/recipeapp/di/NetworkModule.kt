package com.example.recipeapp.di

import com.example.recipeapp.util.Util
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(Util.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }
}