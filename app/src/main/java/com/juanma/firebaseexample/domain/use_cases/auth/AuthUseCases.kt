package com.juanma.firebaseexample.domain.use_cases.auth

data class AuthUseCases(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val signInWithEmailAndPasswordUseCase: SignInWithEmailAndPasswordUseCase,
    val signInAnonymouslyUseCase: SignInAnonymouslyUseCase,
    val signUpUseCase: SignUpUseCase,
    val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    val resetPasswordUseCase: ResetPasswordUseCase,
    val validateNewUserUseCase: ValidateNewUserUseCase,
    val signOutUseCase: SignOutUseCase
)
