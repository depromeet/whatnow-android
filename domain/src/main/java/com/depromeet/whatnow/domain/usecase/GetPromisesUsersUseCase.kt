package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesUsersStatus
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesUsersUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(promise_id: Int): Result<List<PromisesUsersStatus>> =
        promisesRepository.getPromisesUsers(promise_id = promise_id)
}