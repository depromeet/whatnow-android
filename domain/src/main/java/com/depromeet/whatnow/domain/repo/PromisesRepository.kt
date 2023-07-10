package com.depromeet.whatnow.domain.repo



import com.depromeet.whatnow.domain.model.*

interface PromisesRepository {
    suspend fun getLocation(location: String): Result<NcpMapInfo>

    suspend fun getPromisesActive(promise_id: Int): Result<Boolean>

    suspend fun putPromisesUsersLocation(
        promise_id: Int,
        userLocation: CoordinateVo
    ): Result<List<PromisesUsersLocation>>

    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUserList>

    suspend fun getPromisesUsersStatus(status: String): Result<List<GetPromisesUsersStatus>>

    suspend fun getPromises(promise_id: Int): Result<GetPromises>

    suspend fun getPromisesUsers(promise_id: String): Result<PromisesUsersStatusList>

    suspend fun getPromisesUsersSeparated(): Result<PromisesUsersSeparatedList>

    suspend fun patchPromisesProgress(
        progressCode: String,
        promiseId: Int
    ): Result<PromisesProgress>

    suspend fun getPromisesUsersProgress(
        promiseId: Int,
        userId: Int
    ): Result<PromisesProgress>

    suspend fun getPromisesProgress(): Result<GetPromisesProgressList>

    suspend fun postPromisesImagesSuccess(
        promiseId: Int,
        imageKey: String,
        imageCommentType: String
    ): Unit

    suspend fun getPromisesImages(
        promiseId: Int,
        fileExtension: String,
    ): Result<PromisesImages>

    suspend fun postPromisesInteractionsTarget(
        promiseId: Int,
        interactionType: String,
        targetUserId: Int,
    ): Unit

    suspend fun getPromisesInteractions(promiseId: Int): Result<GetPromisesInteractions>

    suspend fun getPromisesInteractionsDetail(
        promiseId: Int,
        interactionType: String
    ): Result<PromisesInteractionsDetail>

    suspend fun postPromises(request: Promise): Result<Promise>
}