package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.PromisesUsersSeparatedList
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetPromisesUsersSeparatedUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(): Result<PromisesUsersSeparatedList> =
        promisesRepository.getPromisesUsersSeparated()
}