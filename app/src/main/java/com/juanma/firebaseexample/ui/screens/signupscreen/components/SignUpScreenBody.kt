package com.juanma.firebaseexample.ui.screens.signupscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
fun SignUpScreenBody(
    modifier: Modifier,
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
                .padding(vertical = 8.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.baseline_person_add_alt_1_24),
                contentDescription = stringResource(id = R.string.login_screen_img_description),
                modifier = Modifier
                    .size(80.dp)
                    .padding(top = 8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            DefaultTextField(
                value = userNameValue,
                label = stringResource(id = R.string.signup_screen_field_username_name),
                onValueChange = userNameInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                validateField = userNameValidateField,
                description = stringResource(id = R.string.signup_screen_field_username_description),
                icon = Icons.Filled.Person,
                keyboard = KeyboardType.Text,

                )

            DefaultTextField(
                value = emailValue,
                label = stringResource(id = R.string.login_screen_field_email_name),
                onValueChange = emailInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                validateField = emailValidateField,
                description = stringResource(id = R.string.login_screen_field_email_description),
                icon = Icons.Filled.Email,
                keyboard = KeyboardType.Email,
            )

            DefaultTextField(
                value = passwordValue,
                label = stringResource(id = R.string.signup_screen_field_password_name),
                onValueChange = passwordInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                validateField = passwordValidateField,
                description = stringResource(id = R.string.signup_screen_field_password_description),
                icon = Icons.Filled.Lock,
                keyboard = KeyboardType.Password,
            )

            DefaultTextField(
                value = passwordConfirmValue,
                label = stringResource(id = R.string.signup_screen_field_confirmpassword_name),
                onValueChange = passwordConfirmInput,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                validateField = passwordConfirmValidateField,
                description = stringResource(id = R.string.signup_screen_field_confirmpassword_description),
                icon = Icons.Filled.Lock,
                keyboard = KeyboardType.Password,
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
                description = stringResource(id = R.string.signup_screen_btn_signup_description),
                onClick = onClickSignUp,
                enabled = btnEnable,
            )

        }
    }
}