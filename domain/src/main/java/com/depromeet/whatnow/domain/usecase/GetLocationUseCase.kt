package com.depromeet.whatnow.domain.usecase

import com.depromeet.whatnow.domain.model.Location
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(
    private val promisesRepository: PromisesRepository
) {
    suspend operator fun invoke(location : String): Result<NcpMapInfo> = promisesRepository.getLocation(location)
}