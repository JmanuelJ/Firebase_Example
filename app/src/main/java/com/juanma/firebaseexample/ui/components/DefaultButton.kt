package com.juanma.firebaseexample.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(
    modifier: Modifier,
    text: String = "",
    description: String = "",
    onClick: () -> Unit,
    icon: ImageVector = Icons.Default.ArrowForward,
    enabled: Boolean = false
) {
    Button(
        onClick = {
            onClick.invoke()
        },
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
        ),

        ) {
        Text(
            text = text,
            modifier = Modifier,
            style = MaterialTheme.typography.titleLarge
        )
        Icon(
            imageVector = icon,
            contentDescription = description,
            modifier = Modifier
                .padding(start = 3.dp)
                .size(40.dp)
        )
    }
}