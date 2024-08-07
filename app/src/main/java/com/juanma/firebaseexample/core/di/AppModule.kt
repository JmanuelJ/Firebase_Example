package com.juanma.firebaseexample.core.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.juanma.firebaseexample.core.Constants.USERS
import com.juanma.firebaseexample.data.network.AuthRepositoryImpl
import com.juanma.firebaseexample.data.network.UsersRepositoryImpl
import com.juanma.firebaseexample.domain.repository.AuthRepository
import com.juanma.firebaseexample.domain.repository.UsersRepository
import com.juanma.firebaseexample.domain.use_cases.auth.AuthUseCases
import com.juanma.firebaseexample.domain.use_cases.auth.GetCurrentUserUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.ResetPasswordUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignInAnonymouslyUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignInWithEmailAndPasswordUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignInWithGoogleUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignOutUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.SignUpUseCase
import com.juanma.firebaseexample.domain.use_cases.auth.ValidateNewUserUseCase
import com.juanma.firebaseexample.domain.use_cases.user.CreateUserUseCase
import com.juanma.firebaseexample.domain.use_cases.user.GetUserByIdUseCase
import com.juanma.firebaseexample.domain.use_cases.user.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Named(USERS)
    fun provideUserRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore():FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        createUserUseCase = CreateUserUseCase(repository),
        getUserByIdUseCase = GetUserByIdUseCase(repository)
    )

    @Provides
    fun provideAuthUseCase(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUserUseCase = GetCurrentUserUseCase(repository),
        resetPasswordUseCase = ResetPasswordUseCase(repository),
        signInWithEmailAndPasswordUseCase = SignInWithEmailAndPasswordUseCase(repository),
        signInAnonymouslyUseCase = SignInAnonymouslyUseCase(repository),
        signInWithGoogleUseCase = SignInWithGoogleUseCase(repository),
        signUpUseCase = SignUpUseCase(repository),
        signOutUseCase = SignOutUseCase(repository),
        validateNewUserUseCase = ValidateNewUserUseCase(repository)
    )


}