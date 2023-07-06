package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.GetPromisesUsersStatusList
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesUsersStatusUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(status: String): Result<GetPromisesUsersStatusList> =
        promisesRepository.getPromisesUsersStatus(status = status)
}