package com.juanma.firebaseexample.domain.use_cases.auth

import com.google.firebase.auth.AuthCredential
import com.juanma.firebaseexample.data.network.AuthManagerService
import javax.inject.Inject

class SignInWithGoogleCredentialUseCase @Inject constructor(
    private val authManagerService: AuthManagerService
) {
    suspend operator fun invoke(credential: AuthCredential) =
        authManagerService.signInWithGoogleCredential(credential)
}