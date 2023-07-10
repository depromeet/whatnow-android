package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class PutPromisesUsersLocationUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(promise_id: Int, userLocation: CoordinateVo) =
        promisesRepository.putPromisesUsersLocation(
            promise_id = promise_id,
            userLocation = userLocation
        )
}