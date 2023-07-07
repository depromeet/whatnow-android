package com.depromeet.whatnow.domain.repo


import com.depromeet.whatnow.domain.model.GetPromisesInteractions
import com.depromeet.whatnow.domain.model.GetPromisesProgressList
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatusList
import com.depromeet.whatnow.domain.model.Location
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.PromisesInteractionsDetail
import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList
import com.depromeet.whatnow.domain.model.PromisesProgress
import com.depromeet.whatnow.domain.model.PromisesUsersSeparatedList
import com.depromeet.whatnow.domain.model.PromisesUsersStatusList

interface PromisesRepository {
    suspend fun getLocation(request: Location): Result<NcpMapInfo>

    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUserList>

    suspend fun getPromisesUsersStatus(status: String): Result<GetPromisesUsersStatusList>

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

}