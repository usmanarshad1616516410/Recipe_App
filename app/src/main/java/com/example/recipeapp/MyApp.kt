package com.example.recipeapp

import android.app.Application
import android.net.Network
import com.example.recipeapp.di.appModule
import com.example.recipeapp.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule, networkModule)
        }
    }
}