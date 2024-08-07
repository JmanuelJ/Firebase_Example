package com.juanma.firebaseexample.data.network

import com.google.firebase.firestore.CollectionReference
import com.juanma.firebaseexample.core.Constants.USERS
import com.juanma.firebaseexample.domain.model.Response
import com.juanma.firebaseexample.domain.model.User
import com.juanma.firebaseexample.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val userRef: CollectionReference
): UsersRepository {

    override suspend fun createUser(user: User): Response<Boolean> {
        return try {
            user.password = ""
            userRef.document(user.id).set(user).await()
            Response.Success(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Error(e.toString())
        }
    }

    override fun getUserById(id: String)= callbackFlow{
        val snapshotListener = userRef.document(id).addSnapshotListener{ snapshot, _ ->
            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

}