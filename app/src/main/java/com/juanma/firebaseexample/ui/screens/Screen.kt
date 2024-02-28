package com.juanma.firebaseexample.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.juanma.firebaseexample.ui.theme.FirebaseExampleTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    FirebaseExampleTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            content()
        }
    }
}