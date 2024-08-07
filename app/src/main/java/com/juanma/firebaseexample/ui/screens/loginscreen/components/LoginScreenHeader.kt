package com.juanma.firebaseexample.ui.screens.loginscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.juanma.firebaseexample.R
import com.juanma.firebaseexample.ui.theme.backgroundColor

@Composable
fun  LoginScreenHeader(
    modifier: Modifier
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (background, title) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = backgroundColor
                )
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = stringResource(id = R.string.login_screen_title),
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top, 48.dp)
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end, 8.dp)
                },
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}