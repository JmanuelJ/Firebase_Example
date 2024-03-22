package com.juanma.firebaseexample.domain.use_cases.auth

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.juanma.firebaseexample.data.network.AuthManagerService
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val authManagerService: AuthManagerService
) {
    operator fun invoke(googleSignInLauncher: ActivityResultLauncher<Intent>) =
        authManagerService.signInWithGoogle(googleSignInLauncher)
}