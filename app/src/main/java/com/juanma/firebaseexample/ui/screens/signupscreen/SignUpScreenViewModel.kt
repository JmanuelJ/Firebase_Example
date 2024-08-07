package com.juanma.firebaseexample.ui.screens.signupscreen

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.domain.model.User
import com.juanma.firebaseexample.domain.use_cases.auth.AuthUseCases
import com.juanma.firebaseexample.domain.use_cases.user.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpScreenViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(SignUpScreenState())
    val state: StateFlow<SignUpScreenState> = _state.asStateFlow()

    var user = User()

    fun onUserNameInput(userName: String) {
        _state.update { it.copy(userName = userName) }
    }

    fun onEmailInput(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun onPasswordInput(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun onPasswordConfirmInput(passwordConfirm: String) {
        _state.update { it.copy(passwordConfirm = passwordConfirm) }
    }

    fun validateName() {
        if (state.value.userName.length >= 6) {
            updateUserNameInfo(true, " ")
        } else {
            updateUserNameInfo(false, "Al menos 5 caracteres")
        }
        enableButton()
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.value.email).matches()) {
            updateEmailInfo(true, " ")
        } else {
            updateEmailInfo(false, "Email no valido")
        }
        enableButton()
    }

    fun validatePassword() {
        if (state.value.password.length >= 8) {
            updatePasswordInfo(true, " ")
        } else {
            updatePasswordInfo(false, "Al menos 8 caracteres")
        }
        enableButton()
    }

    fun validatePasswordConfirm() {
        if (state.value.password == state.value.passwordConfirm) {
            updatePasswordConfirmInfo(true, " ")
        } else {
            updatePasswordConfirmInfo(false, "Contraseñas no coinciden")
        }
        enableButton()
    }

    private fun updateUserNameInfo(valid: Boolean, msg: String) {
        _state.update {
            it.copy(
                isUserNameIsValid = valid,
                userNameErrorMsg = msg
            )
        }
    }

    private fun updateEmailInfo(valid: Boolean, msg: String) {
        _state.update {
            it.copy(
                isEmailIsValid = valid,
                emailErrorMsg = msg
            )
        }
    }

    private fun updatePasswordInfo(valid: Boolean, msg: String) {
        _state.update {
            it.copy(
                isPasswordValid = valid,
                passwordErrorMsg = msg
            )
        }
    }

    private fun updatePasswordConfirmInfo(valid: Boolean, msg: String) {
        _state.update {
            it.copy(
                isPasswordConfirmValid = valid,
                passwordConfirmErrorMsg = msg
            )
        }
    }

    private fun enableButton() {
        state.value.apply {
            if (isUserNameIsValid && isEmailIsValid && isPasswordValid && isPasswordConfirmValid) {
                _state.update {
                    it.copy(btnSignUp = true)
                }
            }
        }
    }

    fun onCreateUser() {
        user.apply {
            userName = state.value.userName
            email = state.value.email
            password = state.value.password
        }
        signUp(user)
    }

    private fun signUp(user: User) = viewModelScope.launch {
        _state.update { it.copy(signUpResponse = Response.Loading) }
        val result = authUseCases.signUpUseCase.invoke(user)
        _state.update { it.copy(signUpResponse = result) }
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUserUseCase()!!.uid
        usersUseCases.createUserUseCase(user)
    }
    fun resetSignUpResponse() {
        _state.update {
            it.copy(
                signUpResponse = null,
            )
        }
    }

    fun resetValues() {
        _state.update {
            it.copy(
                signUpResponse = null,
                userName = "",
                isUserNameIsValid = false,
                userNameErrorMsg = "",
                email = "",
                isEmailIsValid = false,
                emailErrorMsg = "",
                password = "",
                isPasswordValid = false,
                passwordErrorMsg = "",
                passwordConfirm = "",
                isPasswordConfirmValid = false,
                passwordConfirmErrorMsg = "",
                btnSignUp = false
            )
        }
    }
}