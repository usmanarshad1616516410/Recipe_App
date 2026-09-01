package com.example.recipeapp.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.example.recipeapp.shared.components.RecipeChip

@Composable
fun DetailScreen(
    state: DetailState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        state.response?.let { response ->

            val totalTime =
                response.prepTimeMinutes + response.cookTimeMinutes

            Box(
                modifier = Modifier.fillMaxHeight(0.4f)
            ) {
                AsyncImage(
                    contentDescription = null,
                    model = response.imageUrl,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
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
            }

            BoxWithConstraints(
                modifier = Modifier.fillMaxSize()
            ) {

                val topPadding = maxHeight * 0.3f
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = topPadding,
                            start = 16.dp,
                            end = 16.dp
                        )
                        .zIndex(1f),

                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "⭐ ${response.rating}",
                        color = Color.White,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 15.dp
                        )
                    )

                    Text(
                        text = "🕒 $totalTime min",
                        color = Color.White,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 15.dp
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.65f)
                        .align(Alignment.BottomCenter)
                        .clip(
                            RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp
                            )
                        )
                        .background(Color.White)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(
                            top = 16.dp,
                            bottom = 20.dp
                        )
                    ) {

                        item {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {

                                Text(
                                    text = "Meal Type",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                response.mealType.forEach { meal ->

                                    Text(
                                        text = "🍽 $meal",
                                        fontSize = 16.sp,
                                        modifier = Modifier.padding(
                                            vertical = 4.dp
                                        )
                                    )
                                }

                                Spacer(
                                    modifier = Modifier.height(20.dp)
                                )

                                Text(
                                    text = response.title,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp,
                                    modifier = Modifier.fillMaxWidth()
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                Text(
                                    text = response.instructions.joinToString(" "),
                                    fontSize = 16.sp,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Justify
                                )

                                Spacer(
                                    modifier = Modifier.height(20.dp)
                                )

                                Text(
                                    text = "Ingredients",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                FlowRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {

                                    response.ingredients.forEach { ingredient ->

                                    RecipeChip(
                                            isClickable = false,
                                            text = ingredient,
                                        )
                                    }
                                }

                                Spacer(
                                    modifier = Modifier.height(20.dp)
                                )

                                Text(
                                    text = "Tags",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 25.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(12.dp)
                                )

                                FlowRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {

                                    response.tags.forEach { tag ->

                                        RecipeChip(
                                            isClickable = false,
                                            text = tag,
                                        )
                                    }
                                }
                                Spacer(
                                    modifier = Modifier
                                        .height(20.dp)
                                        .navigationBarsPadding()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


//    if (state.isLoading) {
//        CircularProgressIndicator(
//            modifier = Modifier.align(Alignment.Center)
//        )
//    }
//}
//}