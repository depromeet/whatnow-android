package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesImagesUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(
        promiseId: Int, fileExtension: String
    ): Result<PromisesImages> =
        promisesRepository.getPromisesImages(promiseId = promiseId, fileExtension = fileExtension)
}