package com.example.recipeapp.util

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun WebpNetworkImage(imageUrl: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = "Loaded WebP image",
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(200.dp)
    )
}
