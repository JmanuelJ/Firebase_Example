package com.juanma.firebaseexample.domain.use_cases.auth

import com.juanma.firebaseexample.domain.model.User
import com.juanma.firebaseexample.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(user: User) = repository.signUp(user)
}