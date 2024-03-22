package com.juanma.firebaseexample.domain.use_cases.auth

import com.juanma.firebaseexample.data.network.AuthManagerService
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val authManagerService: AuthManagerService
) {
    suspend operator fun invoke(
        name: String,
        password: String
    ) = authManagerService.signInWithEmailAndPassword(name, password)
}