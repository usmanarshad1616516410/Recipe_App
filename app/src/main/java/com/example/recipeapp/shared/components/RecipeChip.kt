package com.example.recipeapp.shared.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecipeChip(
    text: String,
    selected: Boolean = false,
    isClickable: Boolean = true,
    shape: Shape = RoundedCornerShape(50.dp),
    textColor: Color = Color.White,
    onClick: () -> Unit = {}
) {

    val isCLickableModifier = if (isClickable) {
        Modifier.clickable {
            onClick()
        }
    } else {
        Modifier
    }
    Surface(
        modifier = Modifier.then(
            isCLickableModifier
        ),
        shape = shape,
        color = if (selected || isClickable.not()) {
            Color.Black
        } else {
            Color(0xFFE2E4E6)
        },
        border = if (!selected) {
            BorderStroke(1.dp, Color(0xFFE0E0E0))
        } else {
            null
        }
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
            fontSize = 13.sp,
            color = textColor,

        )
    }
}