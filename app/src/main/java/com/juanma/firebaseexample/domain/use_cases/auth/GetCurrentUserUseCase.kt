package com.juanma.firebaseexample.domain.use_cases.auth

import com.juanma.firebaseexample.data.network.AuthManagerService
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authManagerService: AuthManagerService
) {
    operator fun invoke() = authManagerService.getCurrentUser()
}