package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.api.ApiService
import com.depromeet.whatnow.data.model.request.PromiseRequest
import com.depromeet.whatnow.data.model.response.*
import com.depromeet.whatnow.domain.model.CoordinateVo
import javax.inject.Inject

internal class PromisesRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
) : PromisesRemoteDataSource {
    override suspend fun getLocation(location: String): Result<LocationResponse> = runCatching {
        apiService.getLocation(location).data
    }

    override suspend fun putPromisesUsersLocation(
        promise_id: Int, userLocation: CoordinateVo,
    ): Result<List<PromisesUsersLocationResponse>> = runCatching {
        apiService.putPromisesUsersLocation(
            promise_id = promise_id, userLocation = userLocation
        ).data
    }

    override suspend fun getPromisesActive(promise_id: Int): Result<Boolean> = runCatching {
        apiService.getPromisesActive(promise_id = promise_id).data
    }

    override suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUsersListResponse> =
        runCatching {
            apiService.getPromisesMonthlyUsers(year_month = year_month).data
        }

    override suspend fun getPromises(promise_id: Int): Result<GetPromisesResponse> = runCatching {
        apiService.getPromises(promise_id = promise_id).data
    }


    override suspend fun getPromisesUsersStatus(status: String): Result<List<GetPromisesUsersStatusResponse>> =
        runCatching {
            apiService.getPromisesUsersStatus(status = status).data
        }

    override suspend fun getPromisesUsersSeparated(): Result<PromisesUsersSeparatedListResponse> =
        runCatching {
            apiService.getPromisesUsersSeparated().data
        }

    override suspend fun getPromisesUsers(promise_id: Int): Result<List<PromisesUsersStatusResponse>> =
        runCatching {
            apiService.getPromisesUsers(promise_id = promise_id).data
        }

    override suspend fun patchPromisesProgress(
        progressCode: String, promiseId: Int,
    ): Result<PromisesProgressResponse> = runCatching {
        apiService.patchPromisesProgress(
            progressCode = progressCode, promiseId = promiseId
        ).data
    }

    override suspend fun getPromisesUsersProgress(
        promiseId: Int, userId: Int,
    ): Result<PromisesProgressResponse> = runCatching {
        apiService.getPromisesUsersProgress(
            promiseId = promiseId, userId = userId
        ).data
    }

    override suspend fun getPromisesProgress(): Result<List<GetPromisesProgressResponse>> =
        runCatching {
            apiService.getPromisesProgress().data
        }


    override suspend fun postPromisesImagesSuccess(
        promiseId: Int, imageKey: String, imageCommentType: String,
    ) {
        runCatching {
            apiService.postPromisesImagesSuccess(
                promiseId = promiseId, imageKey = imageKey, imageCommentType = imageCommentType
            )
        }
    }

    override suspend fun getPromisesImages(
        promiseId: Int, fileExtension: String,
    ): Result<PromisesImagesResponse> = runCatching {
        apiService.getPromisesImages(
            promiseId = promiseId,
            fileExtension = fileExtension,
        ).data
    }

    override suspend fun postPromisesInteractionsTarget(
        promiseId: Int, interactionType: String, targetUserId: Int,
    ): Result<Unit> = runCatching {
        apiService.postPromisesInteractionsTarget(
            promiseId = promiseId,
            interactionType = interactionType,
            targetUserId = targetUserId
        )
    }

    override suspend fun getPromisesInteractions(promiseId: Int): Result<GetPromisesInteractionsResponse> =
        runCatching {
            apiService.getPromisesInteractions(
                promiseId = promiseId,
            ).data
        }

    override suspend fun getPromisesInteractionsDetail(
        promiseId: Int, interactionType: String,
    ): Result<PromisesInteractionsDetailResponse> = runCatching {
        apiService.getPromisesInteractionsDetail(
            promiseId = promiseId, interactionType = interactionType
        ).data
    }

    override suspend fun postPromises(request: PromiseRequest): Result<PromisesResponse> =
        runCatching {
            apiService.postPromises(request).data
        }

    override suspend fun postPromisesUsers(
        promise_id: String,
        userId: Int,
        userLocation: CoordinateVoResponse,
    ): Result<PromisesUsersCreateResponse> =
        runCatching {
            apiService.postPromisesUsers(
                promise_id = promise_id,
                userId = userId,
                userLocation = userLocation
            ).data
        }

    override suspend fun postUsersJoin(invite_codes: String): Result<PromisesUsersCreateResponse> =
        runCatching {
            apiService.postUsersJoin(invite_codes = invite_codes).data
        }


}