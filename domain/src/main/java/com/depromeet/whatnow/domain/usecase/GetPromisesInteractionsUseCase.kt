package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.GetPromisesInteractions
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesInteractionsUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(
        promiseId: Int,
    ): Result<GetPromisesInteractions> =
        promisesRepository.getPromisesInteractions(
            promiseId = promiseId
        )
}