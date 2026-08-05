package com.example.recipeapp.presentation.details

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.recipeapp.R

@Composable
fun DetailScreen(
    navController: NavHostController,

    viewModel: DetailViewModel = viewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.image3),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            alignment = Alignment.TopStart
        )

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
                text = "Tortilla Pizza",
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )

            Text(
                fontSize = 16.sp,
                text = "This tortilla pizza is extremely easy to make. It is light enough to be a snack, serves well as an appetizer, or is so good that it can be devoured alone! You can use any sort of topping variation.",
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

            val ingredients = listOf("soft flour tortilla", "cheese", "tomato sauce")

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(ingredients) { ingredient ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(height = 90.dp, width = 366.dp)
                            .padding(15.dp)
                            .clip(CutCornerShape(15.dp))
                    ) {
                        Text(
                            text = ingredient,
                            fontSize = 17.sp,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(top = 18.dp, start = 75.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        navController = rememberNavController(),
    )
}