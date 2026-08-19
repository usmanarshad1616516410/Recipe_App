package com.example.recipeapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.recipeapp.navigation.Routes
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = koinViewModel(),

    ) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val response = remember(state.trendingRecipes) {
        state.trendingRecipes.randomOrNull()
    }

    val context = LocalContext.current

    LaunchedEffect(viewModel.effects) {
        viewModel.effects.collect {
            when (it) {
                is HomeEffects.NavigateToDetailScreen -> {
                    it.recipe?.let { response ->
                        navController.navigate(Routes.Detail(response.id))
                    } ?: run {
                        Toast.makeText(context, "No such recipe right now", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.LightGray
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Discover Best Recipes",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
                    .padding(top = 50.dp),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(15.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = state.searchQueryFlow,
                maxLines = 1,
                onValueChange = { viewModel.onIntent(HomeIntent.SearchUpdate(it)) },
                placeholder = {
                    Text("Search any Recipe")
                },
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.onIntent(
                            intent = HomeIntent.SearchRecipe(
                                state.searchQueryFlow
                            )
                        )
                    }) {
                        Icon(
                            painter = painterResource(
                                android.R.drawable.ic_menu_search
                            ), contentDescription = null
                        )
                    }
                }

            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = "Just For You",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Routes.Detail(recipeId = response?.id ?: "")
                                )
                            }
                            .clip(shape = RoundedCornerShape(10.dp)),
                        model = response?.imageUrl,
                        contentDescription = null,

                        )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxSize(0.6f)
                            .align(Alignment.BottomCenter)
                    )

                    Text(
                        text = response?.name ?: String(),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(20.dp)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Trending Recipes",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 25.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                items(items = state.trendingRecipes) { item ->
                    AsyncImage(
                        contentDescription = null,
                        model = item.imageUrl,
                        modifier = Modifier
                            .padding(horizontal = 10.dp, vertical = 20.dp)
                            .size(210.dp)
                            .clip(shape = RoundedCornerShape(10.dp))
                            .clickable {
                                navController.navigate (
                                    Routes.Detail(recipeId = item.id)
                                )

                            }
                    )
                }
            }
        }
    }

}
