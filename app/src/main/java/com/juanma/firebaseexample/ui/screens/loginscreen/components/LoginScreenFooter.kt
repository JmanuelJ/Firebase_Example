package com.juanma.firebaseexample.ui.screens.loginscreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.juanma.firebaseexample.R

@Composable
fun LoginScreenFooter(
 modifier: Modifier,
 onClickCreate: () -> Unit
) {
    Row(
       modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.login_screen_btn_ask_account),
            modifier = Modifier
                .padding(end = 16.dp),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = stringResource(id = R.string.login_screen_btn_create_account),
            modifier = Modifier
                .padding(end = 16.dp)
                .clickable {onClickCreate.invoke() },
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.labelLarge
        )
    }
}