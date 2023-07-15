package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.CoordinateVo
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class PostPromisesUsersCreate @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(promise_id: String, userId: Int, userLocation: CoordinateVo) =
        promisesRepository.postPromisesUsers(
            promise_id = promise_id,
            userId = userId,
            userLocation = userLocation
        )
}