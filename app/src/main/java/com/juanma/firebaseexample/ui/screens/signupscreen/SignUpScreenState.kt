package com.juanma.firebaseexample.ui.screens.signupscreen

import com.google.firebase.auth.FirebaseUser
import com.juanma.firebaseexample.domain.model.Response

data class SignUpScreenState(
    var signUpResponse: Response<FirebaseUser>? = null,
    var userName: String = "",
    var isUserNameIsValid: Boolean = false,
    var userNameErrorMsg: String = "",
    var email: String = "",
    var isEmailIsValid: Boolean = false,
    var emailErrorMsg: String = "",
    var password: String = "",
    var isPasswordValid: Boolean = false,
    var passwordErrorMsg: String = "",
    var passwordConfirm: String = "",
    var isPasswordConfirmValid: Boolean = false,
    var passwordConfirmErrorMsg: String = "",
    var btnSignUp: Boolean = false
)
