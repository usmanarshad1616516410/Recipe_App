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
                modifier = Modifier.fillMaxHeight(0.5f)
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
                        .fillMaxHeight(0.4f)
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

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                // Calculate 40% of the maximum screen height
                val topPadding = maxHeight * 0.4f
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = topPadding,
                        bottom = 20.dp
                    )
                ) {

                    item {
                        Text(
                            text = "⭐ ${response.rating}",
                            color = Color.White,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(
                                horizontal = 16.dp
                            )
                        )
                    }

                    item {
                        Text(
                            text = "🕒 $totalTime min",
                            color = Color.White,
                            fontSize = 13.sp,
                            modifier = Modifier.padding(
                                horizontal = 16.dp,
                                vertical = 16.dp
                            )
                        )
                    }

                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(
                                    RoundedCornerShape(
                                        topStart = 20.dp,
                                        topEnd = 20.dp
                                    )
                                )
                                .background(Color.White)
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.Top
                            ) {

                                Column(modifier = Modifier
                                    .padding(15.dp)
                                    .weight(1f)) {


                                    Text(
                                        text = "Meal Type",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
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
                                }

                                Spacer(
                                    modifier = Modifier.height(16.dp)
                                )
                                Column(
                                    modifier = Modifier
                                        .padding(15.dp)
                                        .weight(1f),

                                    horizontalAlignment = Alignment.End
                                )


                                {
                                    Text(
                                        text = "Tags",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )

                                    Spacer(
                                        modifier = Modifier.height(8.dp),
                                    )

                                    response.tags.forEach { tag ->

                                        Text(
                                            text = "#$tag",
                                            fontSize = 15.sp,
                                            modifier = Modifier.padding(
                                                vertical = 3.dp
                                            )
                                        )
                                    }
                                }
                            }

                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )

                            Text(
                                text = response.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 26.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp)
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
                                modifier = Modifier.padding(10.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 30.sp
                            )

                            /*   response.ingredients.forEach { ingredient ->

                                   Card(
                                       modifier = Modifier
                                           .fillMaxWidth()
                                           .padding(
                                               horizontal = 10.dp,
                                               vertical = 5.dp
                                           ),
                                       shape = RoundedCornerShape(10.dp)
                                   ) {
                                       Text(
                                           text = ingredient,
                                           fontSize = 17.sp,
                                           textAlign = TextAlign.Center,
                                           modifier = Modifier
                                               .fillMaxWidth()
                                               .padding(
                                                   vertical = 20.dp
                                               )
                                       )
                                   }
                               }*/

                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp), // Spacing between chips horizontally
                                verticalArrangement = Arrangement.spacedBy(8.dp)     // Spacing between lines vertically
                            ) {
                                response.ingredients.forEach { ingredient ->
                                    RecipeChip(
                                        isClickable = false,
                                        text = ingredient,
                                    )
                                }
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