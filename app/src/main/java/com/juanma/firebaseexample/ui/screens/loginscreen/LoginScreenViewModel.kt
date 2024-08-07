package com.juanma.firebaseexample.ui.screens.loginscreen

import android.content.Context
import android.content.Intent
import android.util.Patterns
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.juanma.firebaseexample.R
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.domain.use_cases.auth.AuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val authUseCases: AuthUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> = _state.asStateFlow()

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    init {
        if (state.value.currentUser != null) state.value.loginResponse =
            Response.Success(state.value.currentUser!!)
    }

    fun onEmailInput(email: String) {
        _state.update {
            it.copy(
                email = email
            )
        }
    }

    fun onPasswordInput(password: String) {
        _state.update {
            it.copy(
                password = password
            )
        }
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.value.email).matches()) {
            _state.update { it.copy(isEmailValid = true) }
        }
        enableButton()
    }

    fun validatePassword() {
        if (state.value.password.length >= 8) {
            _state.update { it.copy(isPasswordValid = true) }
        }
        enableButton()
    }

    private fun enableButton() {
        state.value.apply {
            _state.update { it.copy(btnEnable = isEmailValid && isPasswordValid) }
        }
    }

    fun onFirebaseLauncher(firebaseLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        _state.update {
            it.copy(
                firebaseGoogleLauncher = firebaseLauncher
            )
        }
    }

    fun signInWithEmailAndPassword() = viewModelScope.launch {
        updateLoginResponse(Response.Loading)
        val authResponse = authUseCases.signInWithEmailAndPasswordUseCase.invoke(
            state.value.email,
            state.value.password
        )
        updateLoginResponse(authResponse)
    }

    fun signInAnonymously() = viewModelScope.launch {
        updateLoginResponse(Response.Loading)
        val authResponse = authUseCases.signInAnonymouslyUseCase.invoke()
        updateLoginResponse(authResponse)
    }

    fun signInWithGoogle(tokenId: String) = viewModelScope.launch {
        updateLoginResponse(Response.Loading)
        val authResponse = authUseCases.signInWithGoogleUseCase.invoke(tokenId)
        updateLoginResponse(authResponse)

    }

    private fun updateLoginResponse(response: Response<FirebaseUser>?) {
        _state.update { it.copy(loginResponse = response) }
    }

    fun launchedGoogleLauncher() {
        val signInIntent = googleSignInClient.signInIntent
        state.value.firebaseGoogleLauncher?.launch(signInIntent)
    }

    fun resetLoginResponse(){
        _state.update {
            it.copy(
                loginResponse = null
            )
        }
    }

    fun resetValues() {
        _state.update {
            it.copy(
                loginResponse = null,
                firebaseGoogleLauncher = null,
                email = "",
                isEmailValid = false,
                password = "",
                isPasswordValid = false,
                btnEnable = false,
                currentUser = null
            )
        }
    }
}