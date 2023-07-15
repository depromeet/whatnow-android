package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.GetPromisesProgress
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesProgressUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(): Result<List<GetPromisesProgress>> =
        promisesRepository.getPromisesProgress()
}