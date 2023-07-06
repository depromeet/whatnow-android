package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesInteractionsDetail
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesInteractionsDetailUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(
        promiseId: Int,
        interactionType: String
    ): Result<PromisesInteractionsDetail> =
        promisesRepository.getPromisesInteractionsDetail(
            promiseId = promiseId,
            interactionType = interactionType
        )
}