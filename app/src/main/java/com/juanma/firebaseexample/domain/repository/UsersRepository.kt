package com.juanma.firebaseexample.domain.repository

import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun createUser(user: User): Response<Boolean>
    fun getUserById(id: String): Flow<User>
}