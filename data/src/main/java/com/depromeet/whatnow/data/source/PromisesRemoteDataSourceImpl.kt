package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.data.model.request.toData
import com.depromeet.whatnow.data.model.response.GetPromisesUsersStatusListResponse
import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsersListResponse
import com.depromeet.whatnow.data.model.response.PromisesProgressResponse
import com.depromeet.whatnow.data.model.response.toData
import javax.inject.Inject

internal class PromisesRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : PromisesRemoteDataSource {
    override suspend fun getLocation(request: LocationEntity): Result<NcpMapInfoEntity> =
        runCatching {
            apiService.getLocation(request.toData()).data.toData()
        }

    override suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUsersListResponse> =
        runCatching {
            apiService.getPromisesMonthlyUsers(year_month = year_month).data
        }

    override suspend fun getPromisesUsersStatus(status: String): Result<GetPromisesUsersStatusListResponse> =
        runCatching {
            apiService.getPromisesUsersStatus(status = status).data
        }

    override suspend fun patchPromisesProgress(
        progressCode: String,
        promiseId: Int
    ): Result<PromisesProgressResponse> =
        runCatching {
            apiService.patchPromisesProgress(
                progressCode = progressCode,
                promiseId = promiseId
            ).data
        }

    override suspend fun getPromisesUsersProgress(
        promiseId: Int,
        userId: Int
    ): Result<PromisesProgressResponse> =
        runCatching {
            apiService.getPromisesUsersProgress(
                promiseId = promiseId,
                userId = userId
            ).data
        }
}