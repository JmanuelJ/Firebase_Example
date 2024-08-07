package com.juanma.firebaseexample.ui.screens.signupscreen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun SignUpScreenContent(
    modifier: Modifier,
    onClickBack: () -> Unit,
    onClick: () -> Unit,
    userNameValue: String,
    userNameInput: (String) -> Unit,
    userNameValidateField: () -> Unit,
    emailValue: String,
    emailInput: (String) -> Unit,
    emailValidateField: () -> Unit,
    passwordValue: String,
    passwordInput: (String) -> Unit,
    passwordValidateField: () -> Unit,
    passwordConfirmValue: String,
    passwordConfirmInput: (String) -> Unit,
    passwordConfirmValidateField: () -> Unit,
    onClickSignUp: () -> Unit,
    btnEnable: Boolean
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (header, body) = createRefs()

        SignUpScreenHeader(
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

        SignUpScreenBody(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(body) {
                    top.linkTo(parent.top, 120.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            userNameValue = userNameValue,
            userNameInput = userNameInput,
            userNameValidateField = userNameValidateField,
            emailValue = emailValue,
            emailInput = emailInput,
            emailValidateField = emailValidateField,
            passwordValue = passwordValue,
            passwordInput = passwordInput,
            passwordValidateField = passwordValidateField,
            passwordConfirmValue = passwordConfirmValue,
            passwordConfirmInput = passwordConfirmInput,
            passwordConfirmValidateField = passwordConfirmValidateField,
            onClickSignUp = onClickSignUp,
            btnEnable = btnEnable
        )

    }
}