package com.juanma.firebaseexample.domain.use_cases.auth

import com.juanma.firebaseexample.data.network.AuthManagerService
import com.juanma.firebaseexample.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getCurrentUser()
}