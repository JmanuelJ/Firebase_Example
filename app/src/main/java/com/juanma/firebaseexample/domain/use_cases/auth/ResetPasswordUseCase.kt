package com.juanma.firebaseexample.domain.use_cases.auth

import com.juanma.firebaseexample.domain.repository.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String) = repository.resetPassword(email)
}