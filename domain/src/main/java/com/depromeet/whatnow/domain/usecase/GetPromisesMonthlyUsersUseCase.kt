package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesMonthlyUsersUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(year_month: String): Result<PromisesMonthlyUserList> =
        promisesRepository.getPromisesMonthlyUsers(year_month = year_month)
}