package com.juanma.firebaseexample.domain.use_cases.auth

import com.juanma.firebaseexample.data.network.AuthManagerService
import javax.inject.Inject

class SignInAnonymouslyUseCase @Inject constructor(
    private val authManagerService: AuthManagerService
) {
    suspend operator fun invoke() = authManagerService.signInAnonymously()
}