package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.entity.LocationEntity
import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.data.model.request.toData
import com.depromeet.whatnow.data.model.response.GetPromisesInteractionsResponse
import com.depromeet.whatnow.data.model.response.GetPromisesProgressListResponse
import com.depromeet.whatnow.data.model.response.GetPromisesUsersStatusListResponse
import com.depromeet.whatnow.data.model.response.PromisesImagesResponse
import com.depromeet.whatnow.data.model.response.PromisesInteractionsDetailResponse
import com.depromeet.whatnow.data.model.response.PromisesMonthlyUsersListResponse
import com.depromeet.whatnow.data.model.response.PromisesProgressResponse
import com.depromeet.whatnow.data.model.response.PromisesUsersSeparatedListResponse
import com.depromeet.whatnow.data.model.response.PromisesUsersStatusListResponse
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

    override suspend fun getPromisesUsersSeparated(): Result<PromisesUsersSeparatedListResponse> =
        runCatching {
            apiService.getPromisesUsersSeparated().data
        }

    override suspend fun getPromisesUsers(promise_id: String): Result<PromisesUsersStatusListResponse> =
        runCatching {
            apiService.getPromisesUsers(promise_id = promise_id).data
        }

    override suspend fun patchPromisesProgress(
        progressCode: String, promiseId: Int
    ): Result<PromisesProgressResponse> = runCatching {
        apiService.patchPromisesProgress(
            progressCode = progressCode, promiseId = promiseId
        ).data
    }

    override suspend fun getPromisesUsersProgress(
        promiseId: Int, userId: Int
    ): Result<PromisesProgressResponse> = runCatching {
        apiService.getPromisesUsersProgress(
            promiseId = promiseId, userId = userId
        ).data
    }

    override suspend fun getPromisesProgress(): Result<GetPromisesProgressListResponse> =
        runCatching {
            apiService.getPromisesProgress().data
        }


    override suspend fun postPromisesImagesSuccess(
        promiseId: Int, imageKey: String, imageCommentType: String
    ) {
        runCatching {
            apiService.postPromisesImagesSuccess(
                promiseId = promiseId, imageKey = imageKey, imageCommentType = imageCommentType
            )
        }
    }

    override suspend fun getPromisesImages(
        promiseId: Int, fileExtension: String
    ): Result<PromisesImagesResponse> = runCatching {
        apiService.getPromisesImages(
            promiseId = promiseId,
            fileExtension = fileExtension,
        ).data
    }

    override suspend fun postPromisesInteractionsTarget(
        promiseId: Int, interactionType: String, targetUserId: Int
    ) {
        runCatching {
            apiService.postPromisesInteractionsTarget(
                promiseId = promiseId,
                interactionType = interactionType,
                targetUserId = targetUserId
            )
        }
    }

    override suspend fun getPromisesInteractions(promiseId: Int): Result<GetPromisesInteractionsResponse> =
        runCatching {
            apiService.getPromisesInteractions(
                promiseId = promiseId,
            ).data
        }

    override suspend fun getPromisesInteractionsDetail(
        promiseId: Int, interactionType: String
    ): Result<PromisesInteractionsDetailResponse> = runCatching {
        apiService.getPromisesInteractionsDetail(
            promiseId = promiseId, interactionType = interactionType
        ).data
    }
}