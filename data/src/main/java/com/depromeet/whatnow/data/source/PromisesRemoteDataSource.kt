package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.data.model.response.GetPromisesInteractionsResponse
import com.depromeet.whatnow.data.model.response.GetPromisesProgressListResponse
import com.depromeet.whatnow.data.model.response.GetPromisesUsersStatusListResponse
import com.depromeet.whatnow.data.model.response.PromisesImagesResponse
import com.depromeet.whatnow.data.model.response.PromisesInteractionsDetailResponse
import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsersListResponse
import com.depromeet.whatnow.data.model.response.PromisesProgressResponse
import com.depromeet.whatnow.data.model.response.PromisesUsersSeparatedListResponse
import com.depromeet.whatnow.data.model.response.PromisesUsersStatusListResponse

interface PromisesRemoteDataSource {
    suspend fun getLocation(request: LocationEntity): Result<NcpMapInfoEntity>
    suspend fun getPromisesActive(promise_id: Int): Result<Boolean>
    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUsersListResponse>
    suspend fun getPromisesUsersStatus(status: String): Result<GetPromisesUsersStatusListResponse>

    suspend fun getPromisesUsersSeparated(): Result<PromisesUsersSeparatedListResponse>

    suspend fun getPromisesUsers(promise_id: String): Result<PromisesUsersStatusListResponse>

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

    suspend fun getPromisesImages(
        promiseId: Int,
        fileExtension: String,
    ): Result<PromisesImagesResponse>

    suspend fun postPromisesInteractionsTarget(
        promiseId: Int,
        interactionType: String,
        targetUserId: Int,
    ): Unit

    suspend fun getPromisesInteractions(promiseId: Int): Result<GetPromisesInteractionsResponse>

    suspend fun getPromisesInteractionsDetail(
        promiseId: Int,
        interactionType: String
    ): Result<PromisesInteractionsDetailResponse>


}