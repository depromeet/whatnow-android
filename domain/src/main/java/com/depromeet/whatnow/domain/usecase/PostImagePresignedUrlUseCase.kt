package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.GetImagePresignedUrl
import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

class GetImagePresignedUrlUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(extension: String): Result<GetImagePresignedUrl> =
        usersRepository.getImagePresignedUrl(extension)
}
