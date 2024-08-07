package com.juanma.firebaseexample.ui.screens.forgotpassscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.juanma.firebaseexample.R
import com.juanma.firebaseexample.ui.components.DefaultButton
import com.juanma.firebaseexample.ui.components.DefaultTextField
import com.juanma.firebaseexample.ui.theme.backgroundColor

@Composable
fun ForgotPasswordScreenBody(
    modifier: Modifier,
    emailValue: String,
    emailInput: (String) -> Unit,
    emilValidateField: () -> Unit,
    btnEnable: Boolean,
    onClickForgot: () -> Unit
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.baseline_restore_24),
                contentDescription = stringResource(id = R.string.login_screen_img_description),
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            DefaultTextField(
                value = emailValue,
                label = stringResource(id = R.string.forgot_screen_field_email_name),
                onValueChange = emailInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 16.dp),
                validateField = emilValidateField,
                description = stringResource(id = R.string.forgot_screen_field_email_description),
                icon = Icons.Filled.Email,
                keyboard = KeyboardType.Email,

                )

            DefaultButton(
                modifier = Modifier
                    .size(70.dp)
                    .padding(top = 8.dp)
                    .background(
                        brush = backgroundColor,
                        shape = RoundedCornerShape(40.dp)
                    )
                    .align(Alignment.CenterHorizontally),
                description = stringResource(id = R.string.forgot_screen_btn_forgot_description),
                onClick = onClickForgot,
                enabled = btnEnable,
            )

        }
    }
}