package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class PostPromisesInteractionsTargetUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(
        promiseId: Int,
        interactionType: String,
        targetUserId: Int
    ) =
        promisesRepository.postPromisesInteractionsTarget(
            promiseId = promiseId,
            interactionType = interactionType,
            targetUserId = targetUserId
        )
}