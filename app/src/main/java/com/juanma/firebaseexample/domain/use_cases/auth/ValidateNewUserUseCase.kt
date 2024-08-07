package com.juanma.firebaseexample.domain.use_cases.auth

import com.google.firebase.auth.AuthResult
import com.juanma.firebaseexample.domain.repository.AuthRepository
import javax.inject.Inject

class ValidateNewUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(authResult: AuthResult) = authRepository.validateNewUser(authResult)
}