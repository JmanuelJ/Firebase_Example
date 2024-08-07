package com.juanma.firebaseexample.ui.screens.loginscreen

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import com.google.firebase.auth.FirebaseUser
import com.juanma.firebaseexample.domain.model.Response

data class LoginScreenState (
    var loginResponse: Response<FirebaseUser>? = null,
    var firebaseGoogleLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>? = null,
    var email: String = "",
    var isEmailValid: Boolean = false,
    var password: String = "",
    var isPasswordValid: Boolean = false,
    var btnEnable: Boolean = false,
    var currentUser: FirebaseUser? = null
)