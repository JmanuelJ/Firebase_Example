package com.juanma.firebaseexample.ui.screens.auth.loginscreen.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.juanma.firebaseexample.data.network.AnalyticsManagerService
import com.juanma.firebaseexample.data.response.AuthRes
import com.juanma.firebaseexample.ui.navigation.Routes
import com.juanma.firebaseexample.ui.screens.auth.loginscreen.LoginScreenViewModel

@Composable
fun EmailPassSignIn(
    navController: NavHostController,
    analytics: AnalyticsManagerService,
    loginViewModel: LoginScreenViewModel
) {
    when (val result = loginViewModel.loginResponse.value) {
        is AuthRes.Error -> {
            analytics.logButtonClicked("Error Signup: ${result.errorMessage}")
            Toast.makeText(
                LocalContext.current,
                "Error Signup: ${result.errorMessage}",
                Toast.LENGTH_SHORT
            ).show()
        }

        is AuthRes.Success -> {
            analytics.logButtonClicked("Click: Iniciar sesion correo & contraseña")
            LaunchedEffect(Unit) {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Login.route) {
                        inclusive = true
                    }
                }
            }
        }
        else -> {}
    }
}
