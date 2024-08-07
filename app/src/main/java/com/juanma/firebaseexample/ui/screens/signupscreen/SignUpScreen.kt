package com.juanma.firebaseexample.ui.screens.signupscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.juanma.firebaseexample.R
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.ui.components.ProgressBar
import com.juanma.firebaseexample.ui.components.ShowToast
import com.juanma.firebaseexample.ui.navigation.Routes
import com.juanma.firebaseexample.ui.screens.signupscreen.components.SignUpScreenContent
import com.juanma.firebaseexample.ui.screens.signupscreen.components.SignUpScreenHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpScreenViewModel
) {
    val state by viewModel.state.collectAsState()

    when (val response = state.signUpResponse) {
        is Response.Error -> {
            viewModel.resetSignUpResponse()
            ShowToast(
                context = LocalContext.current,
                message = "Error signUp: ${response.errorMessage}"
            )
        }

        Response.Loading -> {
            ProgressBar(modifier = Modifier.fillMaxSize())
        }

        is Response.Success -> {
            viewModel.resetValues()
            viewModel.createUser()
            ShowToast(
                context = LocalContext.current,
                message = stringResource(id = R.string.signup_screen_msg_create)
            )
            navController.navigate(route = Routes.Login.route)
        }

        null -> {

            Scaffold { padding ->

                SignUpScreenHeader(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(color = MaterialTheme.colorScheme.secondaryContainer),
                    onClickBack = {
                        navController.popBackStack()
                    },
                    onClick = {
                        viewModel.resetValues()
                    }
                )

                SignUpScreenContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(color = MaterialTheme.colorScheme.secondaryContainer),
                    onClickBack = {
                        navController.popBackStack()
                    },
                    onClick = { viewModel.resetValues() },
                    userNameValue = state.userName,
                    userNameInput = {
                        viewModel.onUserNameInput(it)
                    },
                    userNameValidateField = {
                        viewModel.validateName()
                    },
                    emailValue = state.email,
                    emailInput = {
                        viewModel.onEmailInput(it)
                    },
                    emailValidateField = {
                        viewModel.validateEmail()
                    },
                    passwordValue = state.password,
                    passwordInput = {
                        viewModel.onPasswordInput(it)
                    },
                    passwordValidateField = {
                        viewModel.validatePassword()
                    },
                    passwordConfirmValue = state.passwordConfirm,
                    passwordConfirmInput = {
                        viewModel.onPasswordConfirmInput(it)
                    },
                    passwordConfirmValidateField = {
                        viewModel.validatePasswordConfirm()
                    },
                    onClickSignUp = {
                        viewModel.onCreateUser()
                    },
                    btnEnable = state.btnSignUp
                )

            }
        }
    }
}













































