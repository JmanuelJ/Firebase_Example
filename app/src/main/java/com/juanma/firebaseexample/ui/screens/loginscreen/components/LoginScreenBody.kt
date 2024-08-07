package com.juanma.firebaseexample.ui.screens.loginscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.juanma.firebaseexample.R
import com.juanma.firebaseexample.ui.components.DefaultButton
import com.juanma.firebaseexample.ui.components.DefaultTextField
import com.juanma.firebaseexample.ui.theme.backgroundColor

@Composable
fun LoginScreenBody(
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
    signInAnonymously: () -> Unit
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
        ) {

            Image(
                painter = painterResource(id = R.drawable.round_person_outline_24),
                contentDescription = stringResource(id = R.string.login_screen_img_description),
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            DefaultTextField(
                value = emailValue,
                label = stringResource(id = R.string.login_screen_field_email_name),
                onValueChange = emailInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                validateField = emilValidateField,
                description = stringResource(id = R.string.login_screen_field_email_description),
                icon = Icons.Filled.Email,
                keyboard = KeyboardType.Email,

                )

            DefaultTextField(
                value = passwordValue,
                label = stringResource(id = R.string.login_screen_field_password_name),
                onValueChange = passwordInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                validateField = passwordValidateField,
                description = stringResource(id = R.string.login_screen_field_password_description),
                keyboard = KeyboardType.Password,
                hideText = true
            )
            Text(
                text = stringResource(id = R.string.login_screen_btn_forgot_password),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 16.dp)
                    .clickable { onClickForgot.invoke() },
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.labelLarge
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
                description = stringResource(id = R.string.login_screen_btn_login_description),
                onClick = onClickLogin,
                enabled = btnEnable,
            )

            SocialButtons(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                signInGoogle = signInGoogle,
                signInAnonymously = signInAnonymously
            )
        }
    }

}

@Composable
fun SocialButtons(
    modifier: Modifier,
    signInGoogle: () -> Unit,
    signInAnonymously: () -> Unit
) {

    Row(
        modifier = modifier,
    ) {
        Button(
            onClick = {
                signInGoogle.invoke()
            },
            modifier = Modifier
                .width(80.dp)
                .height(45.dp)
                .weight(1f)
                .padding(horizontal = 32.dp)
                .background(
                    brush = backgroundColor,
                    shape = RoundedCornerShape(40.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google),
                contentDescription = stringResource(
                    id = R.string.login_screen_btn_google_description
                )
            )
        }
        Button(
            onClick = {
                signInAnonymously.invoke()
            },
            modifier = Modifier
                .width(80.dp)
                .height(45.dp)
                .weight(1f)
                .padding(horizontal = 32.dp)
                .background(
                    brush = backgroundColor,
                    shape = RoundedCornerShape(40.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_incognito),
                contentDescription = stringResource(
                    id = R.string.login_screen_btn_anonymous_description
                )
            )
        }
    }

}