package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository,
) {
    suspend operator fun invoke(promise_id: Int) =
        runCatching { promisesRepository.getPromises(promise_id = promise_id) }
}