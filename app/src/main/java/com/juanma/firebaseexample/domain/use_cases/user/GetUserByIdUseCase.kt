package com.juanma.firebaseexample.domain.use_cases.user

import com.juanma.firebaseexample.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserByIdUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    operator fun invoke(id: String) = usersRepository.getUserById(id)
}