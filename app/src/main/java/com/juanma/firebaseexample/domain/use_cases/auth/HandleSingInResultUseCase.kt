package com.juanma.firebaseexample.domain.use_cases.auth

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.juanma.firebaseexample.data.network.AuthManagerService
import javax.inject.Inject

class HandleSingInResultUseCase @Inject constructor(
    private val authManagerService: AuthManagerService
) {
    operator fun invoke(task: Task<GoogleSignInAccount>) =
        authManagerService.handleSingInResult(task)
}