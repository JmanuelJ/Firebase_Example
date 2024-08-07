package com.juanma.firebaseexample.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DefaultBackButton(
    modifier: Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    description: String
) {
    Icon(
        imageVector = Icons.Default.ArrowBack,
        contentDescription = description,
        modifier = modifier,
        tint = color
    )
}