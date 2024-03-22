package com.juanma.firebaseexample.util

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.juanma.firebaseexample.R
import com.juanma.firebaseexample.data.response.AuthRes
import kotlinx.coroutines.tasks.await

class AuthManager(private val context: Context) {
    private val auth: FirebaseAuth by lazy { Firebase.auth }

    private val signInClient = Identity.getSignInClient(context)

    suspend fun createUserWithEmailAndPassword(
        email: String,password: String
    ): AuthRes<FirebaseUser?> {
        return try{
            val authresult =  auth.signInWithEmailAndPassword(email, password).await()
            AuthRes.Success(authresult.user)
        }catch(e: Exception) {
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }


    suspend fun signInAnonymously(): AuthRes<FirebaseUser?> {
        return try {
            val result = auth.signInAnonymously().await()

            AuthRes.Success(result.user ?: throw Exception("Error al iniciar sesión"))

        } catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    suspend fun resetPassword(email: String): AuthRes<Unit> {
        return try{
            auth.sendPasswordResetEmail(email).await()
            AuthRes.Success(Unit)
        } catch (e: Exception){
            AuthRes.Error(e.message ?: "Error al restablecer contraseña")
        }
    }

    fun signOut(){
        auth.signOut()
        signInClient.signOut()
    }

    fun getCurrentUser(): FirebaseUser?{
        return auth.currentUser
    }

    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    fun handleSingInResult(task: Task<GoogleSignInAccount>): AuthRes<GoogleSignInAccount>?{
        return try{
            val account = task.getResult(ApiException::class.java)
            AuthRes.Success(account)
        } catch(e: ApiException){
            AuthRes.Error(e.message ?: "Google sign-in failed")
        }
    }

    suspend fun signInWithGoogleCredential(credential: AuthCredential): AuthRes<FirebaseUser>?{
        return try{
            val firebaseUser = auth.signInWithCredential(credential).await()
            firebaseUser.user?.let {
                AuthRes.Success(it)
            } ?: throw Exception("Sign in with Google failed.")
        } catch(e: Exception) {
            AuthRes.Error(e.message ?: "Sign in with Google failed.")
        }
    }

    fun signInWithGoogle(googleSignInLauncher: ActivityResultLauncher<Intent>){
        val signInIntent = googleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

}