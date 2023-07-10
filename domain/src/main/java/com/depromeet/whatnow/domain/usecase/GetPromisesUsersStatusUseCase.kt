package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesUsersStatusUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(status: String): Result<List<GetPromisesUsersStatus>> =
        promisesRepository.getPromisesUsersStatus(status = status)
}