package com.juanma.firebaseexample.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    validateField: () -> Unit,
    description: String,
    icon: ImageVector = Icons.Filled.Lock,
    color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    keyboard: KeyboardType = KeyboardType.Text,
    hideText: Boolean = false,
    errorMsg: String = "",

    ) {

    OutlinedTextField(
        value = value,
        onValueChange = {
            onValueChange.invoke(it)
            validateField.invoke()
        },
        modifier = modifier,
        label = {
            Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = description,
                tint = color
            )
        },
        supportingText = {
            Text(
                text = errorMsg,
                modifier = Modifier.padding(2.dp),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge
            )
        },
        visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboard),
    )

}