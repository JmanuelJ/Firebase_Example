package com.juanma.firebaseexample.ui.screens.forgotpassscreen

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
import com.juanma.firebaseexample.ui.screens.forgotpassscreen.components.ForgotPasswordScreenContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: ForgotPasswordScreenViewModel
){
    val state by viewModel.state.collectAsState()

    when (val response = state.forgotResponse){
        is Response.Error -> {
            viewModel.resetForgotResponse()
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
            ShowToast(
                context = LocalContext.current,
                message = stringResource(id = R.string.forgot_screen_msg_forgot)
            )
            navController.navigate(route =Routes.Login.route)
        }
        null -> {

            Scaffold { padding->
                ForgotPasswordScreenContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(color = MaterialTheme.colorScheme.secondaryContainer),
                    onClickBack = {
                        navController.popBackStack()
                    },
                    onClick = { viewModel.resetValues() },
                    emailValue = state.email,
                    emailInput = {
                        viewModel.onEmailInput(it)
                    },
                    emilValidateField = {
                        viewModel.validateEmail()
                    },
                    btnEnable = state.btnForgotEnable,
                    onClickForgot = {
                        viewModel.forgotPass()
                    }
                )

            }
        }
    }
}


















