package com.juanma.firebaseexample.domain.use_cases.user

import com.juanma.firebaseexample.domain.model.User
import com.juanma.firebaseexample.domain.repository.UsersRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {

    suspend operator fun invoke(user: User) = repository.createUser(user)
}