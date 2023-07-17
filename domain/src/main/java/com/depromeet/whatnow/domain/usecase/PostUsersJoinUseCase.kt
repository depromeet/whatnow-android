package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesUsersCreate
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class PostUsersJoinUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository,
) {
    suspend operator fun invoke(invite_code: String): Result<PromisesUsersCreate> =
        promisesRepository.postUsersJoin(invite_code)
}