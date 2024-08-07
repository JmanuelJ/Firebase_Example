package com.juanma.firebaseexample.ui.screens.loginscreen

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
import androidx.navigation.NavController
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.ui.components.ProgressBar
import com.juanma.firebaseexample.ui.components.ShowToast
import com.juanma.firebaseexample.ui.navigation.Routes
import com.juanma.firebaseexample.ui.screens.loginscreen.components.GoogleSignIn
import com.juanma.firebaseexample.ui.screens.loginscreen.components.LoginScreenContent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginScreenViewModel
) {
    val state by viewModel.state.collectAsState()

    when(val response = state.loginResponse){
        is Response.Error -> {
            ShowToast(
                context = LocalContext.current,
                message = "Error signUp: ${response.errorMessage}"
            )
            viewModel.resetLoginResponse()
        }
        Response.Loading -> {
            ProgressBar(modifier = Modifier.fillMaxSize())
        }
        is Response.Success -> {
            navController.navigate(Routes.Home.route){
                popUpTo(Routes.Login.route){
                    inclusive = true
                }
            }
        }
        null -> {
            GoogleSignIn(
                signInGoogle = {
                    viewModel.signInWithGoogle(it)
                },
                onFirebaseLauncher = {
                    viewModel.onFirebaseLauncher(it)
                }
            )
            Scaffold { padding->

                LoginScreenContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(color = MaterialTheme.colorScheme.secondaryContainer),
                    emailValue = state.email,
                    emailInput = {
                        viewModel.onEmailInput(it)
                    },
                    emilValidateField = {
                        viewModel.validateEmail()
                    },
                    passwordValue = state.password,
                    passwordInput = {
                        viewModel.onPasswordInput(it)
                    },
                    passwordValidateField = {
                        viewModel.validatePassword()
                    },
                    onClickForgot = {
                        navController.navigate(Routes.ForgotPassword.route)
                    },
                    btnEnable = state.btnEnable,
                    onClickLogin = {
                        viewModel.signInWithEmailAndPassword()
                    },
                    signInGoogle = {
                        viewModel.launchedGoogleLauncher()
                    },
                    signInAnonymously = {
                        viewModel.signInAnonymously()
                    },
                    onClickCreate = {
                        navController.navigate(Routes.SignUp.route)
                    }
                )

            }
        }
    }
}















