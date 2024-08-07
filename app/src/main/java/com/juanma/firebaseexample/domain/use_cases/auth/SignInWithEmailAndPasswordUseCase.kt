package com.juanma.firebaseexample.domain.use_cases.auth

import com.juanma.firebaseexample.data.network.AuthManagerService
import com.juanma.firebaseexample.domain.repository.AuthRepository
import javax.inject.Inject

class SignInWithEmailAndPasswordUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String) =
        repository.signInWithEmailAndPassword(email, password)
}