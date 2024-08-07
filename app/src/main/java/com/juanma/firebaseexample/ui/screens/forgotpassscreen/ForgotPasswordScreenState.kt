package com.juanma.firebaseexample.ui.screens.forgotpassscreen

import com.juanma.firebaseexample.domain.model.Response

data class ForgotPasswordScreenState(
    var forgotResponse: Response<Boolean>? = null,
    var email: String = "",
    var isEmailValid: Boolean = false,
    var btnForgotEnable: Boolean = false
)