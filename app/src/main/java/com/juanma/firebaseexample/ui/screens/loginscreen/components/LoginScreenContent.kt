package com.juanma.firebaseexample.ui.screens.loginscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun LoginScreenContent(
    modifier: Modifier,
    emailValue: String,
    emailInput: (String) -> Unit,
    emilValidateField: () -> Unit,
    passwordValue: String,
    passwordInput: (String) -> Unit,
    passwordValidateField: () -> Unit,
    onClickForgot: () -> Unit,
    btnEnable: Boolean,
    onClickLogin: () -> Unit,
    signInGoogle: () -> Unit,
    signInAnonymously: () -> Unit,
    onClickCreate: () -> Unit
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (header, body, footer) = createRefs()

        LoginScreenHeader(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        LoginScreenBody(
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
            passwordValue = passwordValue,
            passwordInput = passwordInput,
            passwordValidateField = passwordValidateField,
            onClickForgot = onClickForgot,
            btnEnable = btnEnable,
            onClickLogin = onClickLogin,
            signInGoogle = signInGoogle,
            signInAnonymously = signInAnonymously,
        )


        LoginScreenFooter(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .constrainAs(footer) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, 16.dp)
                },
            onClickCreate = onClickCreate
        )

    }
}