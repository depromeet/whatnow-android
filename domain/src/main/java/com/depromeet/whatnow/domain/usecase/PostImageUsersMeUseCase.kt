package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PostImagesUsersMe
import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

class PostImageUsersMeUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(imageKey: String, extension: String): Result<PostImagesUsersMe> =
        usersRepository.postImageUsersMe(imageKey, extension)
}
