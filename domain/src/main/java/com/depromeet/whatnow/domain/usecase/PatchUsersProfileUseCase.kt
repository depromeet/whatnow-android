package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.UsersProfile
import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

class PatchUsersProfileUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(
        username: String,
        profileImageUrl: String,
        imageKey: String,
        isDefaultImage: Boolean
    ): Result<UsersProfile> =
        usersRepository.patchUsersProfile(username, profileImageUrl, imageKey, isDefaultImage)
}
