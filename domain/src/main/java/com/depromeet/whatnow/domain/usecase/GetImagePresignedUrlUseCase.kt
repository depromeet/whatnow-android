package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.UsersRepository
import java.io.File
import javax.inject.Inject

class PostImagePresignedUrlUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {

    suspend operator fun invoke(url: String, file: File): Result<Unit> =
        usersRepository.postImagePresignedUrl(url, file)
}
