package com.juanma.firebaseexample.ui.screens.auth.loginscreen

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.juanma.firebaseexample.data.response.AuthRes
import com.juanma.firebaseexample.domain.use_cases.auth.GetCurrentUserUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.HandleSingInResultUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignInWithEmailAndPasswordUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignInWithGoogleCredentialUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignInWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val sigInWPUseCase: SignInWithEmailAndPasswordUseCase,
    private val handleSingInResultUseCase: HandleSingInResultUseCase,
    private val signInWithGoogleCredentialUseCase: SignInWithGoogleCredentialUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val getCurrentUser: GetCurrentUserUseCase
) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _loginResponse = MutableLiveData<AuthRes<FirebaseUser?>?>()
    val loginResponse: LiveData<AuthRes<FirebaseUser?>?> = _loginResponse

    private val _googleLoginResponse = MutableLiveData<AuthRes<GoogleSignInAccount>?>()
    val googleLoginResponse: LiveData<AuthRes<GoogleSignInAccount>?> = _googleLoginResponse


    private val _firebaseLauncher =
        MutableLiveData<ManagedActivityResultLauncher<Intent, ActivityResult>>()
    val firebaseLauncher: LiveData<ManagedActivityResultLauncher<Intent, ActivityResult>> =
        _firebaseLauncher

    private val currentUser = getCurrentUser.invoke()

    init {
        if (currentUser != null) _loginResponse.value = AuthRes.Success(currentUser)
    }

    fun onEmailInput(email: String) {
        _email.value = email
    }

    fun onPasswordInput(password: String) {
        _password.value = password
    }

    fun onfirebaseLauncher(firebaseLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        _firebaseLauncher.value = firebaseLauncher
    }

    fun login() = viewModelScope.launch {
        val result = sigInWPUseCase.invoke(email.value!!, password.value!!)
        _loginResponse.value = result
    }

    fun loginWithGoogle(signedInAccountFromIntent: Task<GoogleSignInAccount>) {
        val result = handleSingInResultUseCase.invoke(signedInAccountFromIntent)
        _googleLoginResponse.value = result
    }

    fun signInGoogle(firebaseLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>){
        viewModelScope.launch {
            signInWithGoogleUseCase.invoke(firebaseLauncher)
        }
    }

    fun googleCredential(credential: AuthCredential) = viewModelScope.launch {
        signInWithGoogleCredentialUseCase.invoke(credential)
    }
}