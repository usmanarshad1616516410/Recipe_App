package com.example.recipeapp.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    recipeId: String,
    viewModel: DetailViewModel = koinViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(recipeId) {
        viewModel.onIntent(DetailIntent.LoadRecipe(recipeId))
    }

    Box(modifier = Modifier.fillMaxSize()) {

        state.meal?.let { meal ->
            AsyncImage(
                contentDescription = null,
                model = meal.thumbnailUrl,
                modifier = Modifier.fillMaxSize(),
                alignment = Alignment.TopCenter
            )
        }

        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.error != null -> {
                Text(
                    text = state.error ?: "Something went wrong",
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )
            }

            state.meal != null -> {
                val meal = state.meal!!
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                        .background(color = Color.White)
                        .fillMaxWidth()
                        .size(600.dp)
                        .padding(16.dp)
                        .align(Alignment.BottomEnd)
                ) {
                    Text(
                        text = meal.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                    )

                    Text(
                        fontSize = 16.sp,
                        text = meal.instructions.joinToString(separator = " "),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .verticalScroll(rememberScrollState()),
                        textAlign = TextAlign.Justify
                    )

                    Text(
                        text = "Ingredients",
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp
                    )
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(meal.ingredients) { ingredient ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .size(height = 90.dp, width = 376.dp)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
                                Text(
                                    text = ingredient,
                                    fontSize = 17.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(vertical = 20.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}