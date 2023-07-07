package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class PostPromisesImagesSuccessUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(
        promiseId: Int,
        imageKey: String,
        imageCommentType: String
    ) =
        promisesRepository.postPromisesImagesSuccess(
            promiseId = promiseId,
            imageKey = imageKey,
            imageCommentType = imageCommentType
        )
}