package com.juanma.firebaseexample.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.domain.model.User
import com.juanma.firebaseexample.domain.repository.AuthRepository
import com.juanma.firebaseexample.domain.use_cases.user.UsersUseCases
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val userUse: UsersUseCases
): AuthRepository {

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    override suspend fun signUp(user: User): Response<FirebaseUser> {
        return try {
            val authResult = auth.createUserWithEmailAndPassword(user.email, user.password).await()
            Response.Success(authResult.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.toString())
        }
    }

    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Response<FirebaseUser> {
        return try {
            val authResult = auth.signInWithEmailAndPassword(email, password).await()
            Response.Success(authResult.user!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.toString())
        }
    }

    override suspend fun signInAnonymously(): Response<FirebaseUser> {
        return try {
            val result = auth.signInAnonymously().await()

            Response.Success(result.user ?: throw Exception("Error al iniciar sesión"))

        } catch (e: Exception){
            Response.Error(e.message ?: "Error al iniciar sesión")
        }
    }

    override suspend fun signInWithGoogle(tokenId: String): Response<FirebaseUser> {
        return try {
            val credential = GoogleAuthProvider.getCredential(tokenId, null)
            val authResult = auth.signInWithCredential(credential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            authResult.user?.let { user ->
                if (isNewUser) {
                    createUser(user)
                }
                Response.Success(user)
            } ?: throw Exception("Sign in with Google failed")
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.toString())
        }
    }

    override suspend fun resetPassword(email: String): Response<Boolean> {
        return try {
            auth.sendPasswordResetEmail(email).await()
            Response.Success(true)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Error al restablecer contraseña")
        }
    }

    override suspend fun validateNewUser(authResult: AuthResult): Boolean {
        return authResult.additionalUserInfo?.isNewUser ?: false
    }

    override fun signOut(): Response<Boolean> {
        return try {
            auth.signOut()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.toString())
        }
    }

    private suspend fun createUser(user: FirebaseUser?) {
        user?.apply {
            val newUser = User(
                id = this.uid,
                userName = this.displayName!!,
                email = this.email!!,
                image = this.photoUrl.toString()
            )
            userUse.createUserUseCase(newUser)
        }
    }

}