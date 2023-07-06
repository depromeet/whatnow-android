package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesProgress
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class PatchPromisesProgressUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(
        progressCode: String,
        promiseId: Int
    ): Result<PromisesProgress> =
        promisesRepository.patchPromisesProgress(progressCode = progressCode, promiseId = promiseId)
}