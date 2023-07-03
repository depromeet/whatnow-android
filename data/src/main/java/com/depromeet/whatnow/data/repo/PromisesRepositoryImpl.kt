package com.depromeet.whatnow.data.repo

import com.depromeet.whatnow.data.entity.toData
import com.depromeet.whatnow.data.entity.toDomain
import com.depromeet.whatnow.domain.model.Location
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.depromeet.whatnow.data.source.PromisesRemoteDataSource
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

internal class PromisesRepositoryImpl @Inject constructor(
    private val promisesRemoteDataSource: PromisesRemoteDataSource
) : PromisesRepository{
    override suspend fun getLocation(request: Location): Result<NcpMapInfo> = promisesRemoteDataSource
        .getLocation(request.toData())
        .mapCatching { location -> location.toDomain() }
}