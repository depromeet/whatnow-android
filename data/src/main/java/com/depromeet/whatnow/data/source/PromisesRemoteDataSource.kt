package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsersResponse

interface PromisesRemoteDataSource {
    suspend fun getLocation(request: LocationEntity): Result<NcpMapInfoEntity>
    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUsersResponse>
}