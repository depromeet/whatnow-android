package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.repo.UsersRepository
import javax.inject.Inject

class GetUsersMeImagesUseCase @Inject constructor(
    private val usersRepository: UsersRepository
) {
    suspend operator fun invoke(fileExtension: String): Result<PromisesImages> =
        usersRepository.getUsersMeImages(fileExtension = fileExtension)

}