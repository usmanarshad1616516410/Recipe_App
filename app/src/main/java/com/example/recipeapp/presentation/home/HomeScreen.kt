package com.example.recipeapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.recipeapp.domain.model.FoodTypes
import com.example.recipeapp.shared.components.RecipeChip
import com.example.recipeapp.shared.components.TrendingRecipeCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeState = HomeState(),
    onIntent: (HomeIntent) -> Unit = {}

) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val showErrorSheet = uiState.error != null
    val response = remember(uiState.allRecipes) {
        uiState.allRecipes.randomOrNull()
    }
    val totalTime =
        (response?.prepTimeMinutes ?: 0) +
                (response?.cookTimeMinutes ?: 0)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { paddingValues ->
        when {

            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {

                TextField(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    value = uiState.searchQueryFlow,
                    maxLines = 1,
                    onValueChange = {
                        onIntent(HomeIntent.SearchUpdate(it))
                    },
                    placeholder = {

                        Text("Search any Recipe")


                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFF8F9F5),
                        unfocusedContainerColor = Color(0xFFF8F9F5),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                onIntent(
                                    HomeIntent.SearchRecipe(
                                        uiState.searchQueryFlow
                                    )
                                )
                            }
                        ) {
                            Icon(
                                painter = painterResource(
                                    android.R.drawable.ic_menu_search
                                ),
                                contentDescription = null
                            )
                        }
                    }
                )
            }
            LazyRow(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                items(FoodTypes.entries) { foodType ->
                    RecipeChip(
                        text = foodType.value,
                        selected = foodType == uiState.selectedFoodType,
                        onClick = {
                            onIntent(
                                HomeIntent.FoodTypeClicked(foodType)
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Just For You",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                response?.let {
                                    onIntent(
                                        HomeIntent.ItemClick(
                                            responseId = it.id
                                        )
                                    )
                                }

                            }
                            .clip(shape = RoundedCornerShape(10.dp)),
                        model = response?.imageUrl,
                        contentDescription = null,
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.8f),
                                        Color.Black,
                                    )
                                )
                            )
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)

                    ) {

                        Text(
                            text = "⭐ ${response?.rating}",
                            color = Color.White,
                            fontSize = 13.sp
                        )

                        Text(
                            text = "🕒 $totalTime min",
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }
                    Text(
                        text = response?.title ?: String(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(20.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Trending Recipes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))



            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                items(
                    items = uiState.trendingRecipes,
                    key = { it.id }
                ) { item ->

                    TrendingRecipeCard(
                        title = item.title,
                        imageUrl = item.imageUrl,
                        onClick = {
                            onIntent(
                                HomeIntent.ItemClick(
                                    responseId = item.id
                                )
                            )
                        }
                    )
                }
            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {

                if (showErrorSheet) {

                    ModalBottomSheet(
                        onDismissRequest = {
                            onIntent(HomeIntent.ClearError)
                        },
                        sheetState = sheetState
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = uiState.error,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "Please try again."
                            )

                            Spacer(
                                modifier = Modifier.height(24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}




