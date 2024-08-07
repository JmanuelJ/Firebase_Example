package com.juanma.firebaseexample.ui.screens.loginscreen.components

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException


@Composable
fun GoogleSignIn(
    signInGoogle: (String) -> Unit,
    onFirebaseLauncher: (ManagedActivityResultLauncher<Intent, ActivityResult>) -> Unit
) {
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            signInGoogle.invoke(account.idToken!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    onFirebaseLauncher.invoke(googleSignInLauncher)
}
