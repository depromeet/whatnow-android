package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity

interface PromisesRemoteDataSource {
    suspend fun getLocation(request: LocationEntity): Result<NcpMapInfoEntity>
}