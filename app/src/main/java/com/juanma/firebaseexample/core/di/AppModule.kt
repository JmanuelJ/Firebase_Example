package com.juanma.firebaseexample.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
    /*@Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()*/

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore


}