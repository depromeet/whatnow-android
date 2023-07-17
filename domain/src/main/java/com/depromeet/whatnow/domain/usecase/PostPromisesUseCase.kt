package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.Promise
import com.depromeet.whatnow.domain.model.PromiseDomainResponse
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class PostPromisesUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository,
) {
    suspend operator fun invoke(request: Promise): Result<PromiseDomainResponse> =
        promisesRepository.postPromises(request = request)
}