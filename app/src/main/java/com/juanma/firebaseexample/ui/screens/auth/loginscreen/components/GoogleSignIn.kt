package com.juanma.firebaseexample.ui.screens.auth.loginscreen.components

import android.content.Intent
import android.provider.ContactsContract.CommonDataKinds.Identity
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import com.juanma.firebaseexample.data.network.AnalyticsManagerService
import com.juanma.firebaseexample.data.response.AuthRes
import com.juanma.firebaseexample.ui.navigation.Routes
import com.juanma.firebaseexample.ui.screens.auth.loginscreen.LoginScreenViewModel
import kotlinx.coroutines.launch


@Composable
fun GoogleSignIn(
    navController: NavHostController,
    analytics: AnalyticsManagerService,
    loginViewModel: LoginScreenViewModel
) {
    val context = LocalContext.current
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        loginViewModel.loginWithGoogle(GoogleSignIn.getSignedInAccountFromIntent(result.data))
        when (val account = loginViewModel.googleLoginResponse.value)
             {
            is AuthRes.Error -> {
                analytics.logError("Error SignIn: ${account.errorMessage}")
                Toast
                    .makeText(
                        context,
                        "Error: ${account.errorMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
            }
            is AuthRes.Success -> {
                val credential = GoogleAuthProvider.getCredential(account.data.idToken, null)
                val fireUser = loginViewModel.googleCredential(credential)
                if (fireUser != null) {
                    Toast.makeText(context, "Bienvenido", Toast.LENGTH_SHORT).show()
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                    }
                }
            }

            else -> {
                Toast
                    .makeText(
                        context,
                        "Error desconocido",
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }
    loginViewModel.onfirebaseLauncher(googleSignInLauncher)
}
