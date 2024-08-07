package com.juanma.firebaseexample.ui.screens.forgotpassscreen

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases
): ViewModel() {
    private val _state = MutableStateFlow(ForgotPasswordScreenState())
    val state: StateFlow<ForgotPasswordScreenState> = _state.asStateFlow()

    fun onEmailInput(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.value.email).matches()){
            _state.update { it.copy(isEmailValid = true) }
        }
        enableButton()
    }

    private fun enableButton() {
        _state.update { it.copy(btnForgotEnable = state.value.isEmailValid)}
    }

    fun forgotPass() = viewModelScope.launch {
        _state.update { it.copy(forgotResponse = Response.Loading) }
        val result = authUseCases.resetPasswordUseCase.invoke(state.value.email)
        _state.update { it.copy(forgotResponse = result) }
    }

    fun resetForgotResponse(){
        _state.update {
            it.copy(
                forgotResponse = null,
            )
        }
    }
    fun resetValues(){
        _state.update {
            it.copy(
                forgotResponse = null,
                email = "",
                isEmailValid = false,
                btnForgotEnable = false
            )
        }
    }
}