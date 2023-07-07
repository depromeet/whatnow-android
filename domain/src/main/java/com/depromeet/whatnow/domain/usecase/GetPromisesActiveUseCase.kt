package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesActiveUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(promise_id: Int): Result<Boolean> =
        promisesRepository.getPromisesActive(promise_id = promise_id)
}