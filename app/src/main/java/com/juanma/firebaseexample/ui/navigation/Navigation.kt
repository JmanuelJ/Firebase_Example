package com.juanma.firebaseexample.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.auth.FirebaseUser
import com.juanma.firebaseexample.data.network.AnalyticsManagerService
import com.juanma.firebaseexample.ui.screens.forgotpassscreen.ForgotPasswordScreen
import com.juanma.firebaseexample.ui.screens.forgotpassscreen.ForgotPasswordScreenViewModel
import com.juanma.firebaseexample.ui.screens.homescreen.HomeScreen
import com.juanma.firebaseexample.ui.screens.homescreen.Screen
import com.juanma.firebaseexample.ui.screens.loginscreen.LoginScreen
import com.juanma.firebaseexample.ui.screens.loginscreen.LoginScreenViewModel
import com.juanma.firebaseexample.ui.screens.signupscreen.SignUpScreen
import com.juanma.firebaseexample.ui.screens.signupscreen.SignUpScreenViewModel
import com.juanma.firebaseexample.util.AuthManager

@Composable
fun Navigation(
    context: Context,
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginScreenViewModel,
    forgotPasswordViewModel: ForgotPasswordScreenViewModel
) {
    val analytics: AnalyticsManagerService = AnalyticsManagerService(context)
    val authManager: AuthManager = AuthManager(context)
    val user: FirebaseUser? = authManager.getCurrentUser()

    Screen {
        NavHost(
            navController = navController,
            startDestination = if(user == null) Routes.Login.route else Routes.Home.route
        ) {
            composable(Routes.Login.route) {
                LoginScreen(
                    navController = navController,
                    viewModel = loginViewModel
                )
            }
            composable(Routes.Home.route) {
                HomeScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }
            composable(Routes.SignUp.route) {
                val signUpViewModel: SignUpScreenViewModel = hiltViewModel()
                SignUpScreen(
                    navController = navController,
                    viewModel = signUpViewModel
                )
            }
            composable(Routes.ForgotPassword.route) {
                ForgotPasswordScreen(
                    navController = navController,
                    viewModel = forgotPasswordViewModel
                )
            }
        }
    }
}

@Composable
fun trackScreen(name: String, analytics: FirebaseAnalytics) {
    DisposableEffect(Unit) {
        onDispose {
            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, name)
            }
        }
    }
}