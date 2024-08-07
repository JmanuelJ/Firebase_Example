package com.juanma.firebaseexample.ui.screens.forgotpassscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun ForgotPasswordScreenContent(
    modifier: Modifier,
    onClickBack: () -> Unit,
    onClick: () -> Unit,
    emailValue: String,
    emailInput: (String) -> Unit,
    emilValidateField: () -> Unit,
    btnEnable: Boolean,
    onClickForgot: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (header, body) = createRefs()

        ForgotPasswordScreenHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            onClickBack = onClickBack,
            onClick = onClick
        )

        ForgotPasswordScreenBody(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(body) {
                    top.linkTo(parent.top, 170.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            emailValue = emailValue,
            emailInput = emailInput,
            emilValidateField = emilValidateField,
            btnEnable = btnEnable,
            onClickForgot = onClickForgot
        )

    }
}