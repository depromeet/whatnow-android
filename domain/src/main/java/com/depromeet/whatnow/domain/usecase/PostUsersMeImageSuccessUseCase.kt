package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

class PostUsersMeImageSuccessUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(imageKey: String) =
        usersRepository.postUsersMeImageSuccess(imageKey = imageKey)
}