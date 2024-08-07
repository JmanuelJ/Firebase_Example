package com.juanma.firebaseexample.domain.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.domain.model.User

interface AuthRepository {
    fun getCurrentUser(): FirebaseUser?

    suspend fun signUp(user: User): Response<FirebaseUser>

    suspend fun signInWithEmailAndPassword(email: String, password: String): Response<FirebaseUser>

    suspend fun signInAnonymously(): Response<FirebaseUser>

    suspend fun signInWithGoogle(tokenId: String): Response<FirebaseUser>

    suspend fun resetPassword(email: String): Response<Boolean>

    suspend fun validateNewUser(authResult: AuthResult): Boolean

    fun signOut(): Response<Boolean>
}