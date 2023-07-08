package com.depromeet.whatnow.data.repo

import com.depromeet.whatnow.data.model.response.toData
import com.depromeet.whatnow.data.model.toDomain
import com.depromeet.whatnow.data.source.PromisesRemoteDataSource
import com.depromeet.whatnow.domain.model.GetPromisesInteractions
import com.depromeet.whatnow.domain.model.GetPromisesProgressList
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.PromisesInteractionsDetail
import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList
import com.depromeet.whatnow.domain.model.PromisesProgress
import com.depromeet.whatnow.domain.model.PromisesUsersSeparatedList
import com.depromeet.whatnow.domain.model.PromisesUsersStatusList
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

internal class PromisesRepositoryImpl @Inject constructor(
    private val promisesRemoteDataSource: PromisesRemoteDataSource
) : PromisesRepository {
    override suspend fun getLocation(location: String): Result<NcpMapInfo> =
        promisesRemoteDataSource.getLocation(location)
            .mapCatching { it.toData() }

    override suspend fun getPromisesActive(promise_id: Int): Result<Boolean> =
        promisesRemoteDataSource.getPromisesActive(promise_id = promise_id)
            .mapCatching { it }

    override suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUserList> =
        promisesRemoteDataSource.getPromisesMonthlyUsers(year_month = year_month)
            .mapCatching { promisesMonthlyUsersResponse ->
                promisesMonthlyUsersResponse.contents.toDomain()
            }

    override suspend fun getPromisesUsersStatus(status: String): Result<List<GetPromisesUsersStatus>> =
        promisesRemoteDataSource.getPromisesUsersStatus(status = status).mapCatching {
            it.toDomain().contest
        }

    override suspend fun getPromisesUsers(promise_id: String): Result<PromisesUsersStatusList> =
        promisesRemoteDataSource.getPromisesUsers(promise_id = promise_id).mapCatching {
            it.contest.toDomain()
        }

    override suspend fun getPromisesUsersSeparated(): Result<PromisesUsersSeparatedList> =
        promisesRemoteDataSource.getPromisesUsersSeparated().mapCatching {
            it.toDomain()
        }

    override suspend fun patchPromisesProgress(
        progressCode: String, promiseId: Int
    ): Result<PromisesProgress> = promisesRemoteDataSource.patchPromisesProgress(
        progressCode = progressCode, promiseId = promiseId
    ).mapCatching {
        it.toDomain()
    }

    override suspend fun getPromisesUsersProgress(
        promiseId: Int, userId: Int
    ): Result<PromisesProgress> = promisesRemoteDataSource.getPromisesUsersProgress(
        promiseId = promiseId, userId = userId
    ).mapCatching {
        it.toDomain()
    }

    override suspend fun getPromisesProgress(): Result<GetPromisesProgressList> =
        promisesRemoteDataSource.getPromisesProgress().mapCatching {
            it.contents.toDomain()
        }


    override suspend fun postPromisesImagesSuccess(
        promiseId: Int, imageKey: String, imageCommentType: String
    ) = promisesRemoteDataSource.postPromisesImagesSuccess(
        promiseId = promiseId, imageKey = imageKey, imageCommentType = imageCommentType
    )

    override suspend fun getPromisesImages(
        promiseId: Int, fileExtension: String
    ): Result<PromisesImages> = promisesRemoteDataSource.getPromisesImages(
        promiseId = promiseId, fileExtension = fileExtension
    ).mapCatching {
        it.toDomain()
    }

    override suspend fun postPromisesInteractionsTarget(
        promiseId: Int, interactionType: String, targetUserId: Int
    ) = promisesRemoteDataSource.postPromisesInteractionsTarget(
        promiseId = promiseId, interactionType = interactionType, targetUserId = targetUserId
    )

    override suspend fun getPromisesInteractions(promiseId: Int): Result<GetPromisesInteractions> =
        promisesRemoteDataSource.getPromisesInteractions(
            promiseId = promiseId
        ).mapCatching {
            it.interactionDtoList.toDomain()
        }

    override suspend fun getPromisesInteractionsDetail(
        promiseId: Int, interactionType: String
    ): Result<PromisesInteractionsDetail> = promisesRemoteDataSource.getPromisesInteractionsDetail(
        promiseId = promiseId, interactionType = interactionType
    ).mapCatching {
        it.interactionsDetail.toDomain()
    }
}