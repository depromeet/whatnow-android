package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.UsersProfile
import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

class GetUsersMeUseCase @Inject constructor(
    private val usersRepository: UsersRepository
){
    suspend operator fun invoke(): Result<UsersProfile> =
        usersRepository.getUsersMe()
}