package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.data.model.response.GetPromisesUsersStatusListResponse
import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsersListResponse

interface PromisesRemoteDataSource {
    suspend fun getLocation(request: LocationEntity): Result<NcpMapInfoEntity>
    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUsersListResponse>
    suspend fun getPromisesUsersStatus(status: String): Result<GetPromisesUsersStatusListResponse>

}