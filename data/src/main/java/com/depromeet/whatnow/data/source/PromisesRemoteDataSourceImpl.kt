package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.data.model.toData
import javax.inject.Inject

internal class PromisesRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : PromisesRemoteDataSource {
    override suspend fun getLocation(request: LocationEntity): Result<NcpMapInfoEntity> =
        runCatching {
            apiService.getLocation(request.toData()).data.toData()
        }
}