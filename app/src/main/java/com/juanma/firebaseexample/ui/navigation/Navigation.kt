package com.juanma.firebaseexample.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.auth.FirebaseUser
import com.juanma.firebaseexample.ui.screens.HomeScreen
import com.juanma.firebaseexample.ui.screens.Screen
import com.juanma.firebaseexample.ui.screens.auth.forgotpassscreen.ForgotPasswordScreen
import com.juanma.firebaseexample.ui.screens.auth.loginscreen.LoginScreen
import com.juanma.firebaseexample.ui.screens.auth.signinscreen.SignUpScreen
import com.juanma.firebaseexample.data.network.AnalyticsManagerService
import com.juanma.firebaseexample.data.network.AuthManagerService
import com.juanma.firebaseexample.ui.screens.auth.loginscreen.LoginScreenViewModel
import com.juanma.firebaseexample.util.AuthManager

@Composable
fun Navigation(
    context: Context,
    navController: NavHostController = rememberNavController(),
    loginViewModel: LoginScreenViewModel
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
                    analytics = analytics,
                    auth = authManager,
                    navController = navController,
                    loginViewModel = loginViewModel
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
                SignUpScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
                )
            }
            composable(Routes.ForgotPassword.route) {
                ForgotPasswordScreen(
                    analytics = analytics,
                    auth = authManager,
                    navigation = navController
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