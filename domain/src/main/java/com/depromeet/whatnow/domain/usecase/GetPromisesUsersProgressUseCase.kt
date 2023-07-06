package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesProgress
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesUsersProgressUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(
        promiseId: Int,
        userId: Int
    ): Result<PromisesProgress> =
        promisesRepository.getPromisesUsersProgress(promiseId = promiseId, userId = userId)
}