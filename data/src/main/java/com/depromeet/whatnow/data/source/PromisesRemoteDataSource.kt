package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.data.model.response.GetPromisesProgressListResponse
import com.depromeet.whatnow.data.model.response.GetPromisesUsersStatusListResponse
import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsersListResponse
import com.depromeet.whatnow.data.model.response.PromisesProgressResponse

interface PromisesRemoteDataSource {
    suspend fun getLocation(request: LocationEntity): Result<NcpMapInfoEntity>
    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUsersListResponse>
    suspend fun getPromisesUsersStatus(status: String): Result<GetPromisesUsersStatusListResponse>

    suspend fun patchPromisesProgress(
        progressCode: String,
        promiseId: Int
    ): Result<PromisesProgressResponse>

    suspend fun getPromisesUsersProgress(
        promiseId: Int,
        userId: Int
    ): Result<PromisesProgressResponse>

    suspend fun getPromisesProgress(): Result<GetPromisesProgressListResponse>

    suspend fun postPromisesImagesSuccess(
        promiseId: Int,
        imageKey: String,
        imageCommentType: String
    ): Unit

}