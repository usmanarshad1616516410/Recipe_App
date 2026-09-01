package com.example.recipeapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.recipeapp.navigation.NavigationStack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        WindowCompat.getInsetsController(window, window.decorView)
//            .isAppearanceLightStatusBars = false
        setContent {
            NavigationStack()
        }
    }
}


