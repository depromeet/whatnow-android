package com.depromeet.whatnow.data.repo

import com.depromeet.whatnow.data.model.request.toData
import com.depromeet.whatnow.data.model.response.toDomain
import com.depromeet.whatnow.data.model.toDomain
import com.depromeet.whatnow.data.source.PromisesRemoteDataSource
import com.depromeet.whatnow.domain.model.CoordinateVo
import com.depromeet.whatnow.domain.model.GetPromises
import com.depromeet.whatnow.domain.model.GetPromisesInteractions
import com.depromeet.whatnow.domain.model.GetPromisesProgress
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.depromeet.whatnow.domain.model.Promise
import com.depromeet.whatnow.domain.model.PromisesImages
import com.depromeet.whatnow.domain.model.PromisesInteractionsDetail
import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList
import com.depromeet.whatnow.domain.model.PromisesProgress
import com.depromeet.whatnow.domain.model.PromisesUsersLocation
import com.depromeet.whatnow.domain.model.PromisesUsersSeparatedList
import com.depromeet.whatnow.domain.model.PromisesUsersStatus
import com.depromeet.whatnow.domain.repo.PromisesRepository
import javax.inject.Inject

internal class PromisesRepositoryImpl @Inject constructor(
    private val promisesRemoteDataSource: PromisesRemoteDataSource
) : PromisesRepository {
    override suspend fun postPromises(request: Promise): Result<Promise> =
        promisesRemoteDataSource.postPromises(request.toData())
            .mapCatching { it.toDomain() }


    override suspend fun getLocation(location: String): Result<NcpMapInfo> =
        promisesRemoteDataSource.getLocation(location)
            .mapCatching { it.toDomain() }

    override suspend fun getPromisesActive(promise_id: Int): Result<Boolean> =
        promisesRemoteDataSource.getPromisesActive(promise_id = promise_id)
            .mapCatching { it }

    override suspend fun putPromisesUsersLocation(
        promise_id: Int,
        userLocation: CoordinateVo
    ): Result<List<PromisesUsersLocation>> =
        promisesRemoteDataSource.putPromisesUsersLocation(
            promise_id = promise_id,
            userLocation = userLocation
        ).mapCatching { it.toDomain() }

    override suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUserList> =
        promisesRemoteDataSource.getPromisesMonthlyUsers(year_month = year_month)
            .mapCatching { promisesMonthlyUsersResponse ->
                promisesMonthlyUsersResponse.contents.toDomain()
            }

    override suspend fun getPromisesUsersStatus(status: String): Result<List<GetPromisesUsersStatus>> =
        promisesRemoteDataSource.getPromisesUsersStatus(status = status).mapCatching {
            it.toDomain().contest
        }

    override suspend fun getPromises(promise_id: Int): Result<GetPromises> =
        promisesRemoteDataSource.getPromises(promise_id = promise_id).mapCatching {
            it.toDomain()
        }

    override suspend fun getPromisesUsers(promise_id: Int): Result<List<PromisesUsersStatus>> =
        promisesRemoteDataSource.getPromisesUsers(promise_id = promise_id).mapCatching {
            it.toDomain()
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

    override suspend fun getPromisesProgress(): Result<List<GetPromisesProgress>> =
        promisesRemoteDataSource.getPromisesProgress().mapCatching {
            it.toDomain()
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
            it.toDomain()
        }

    override suspend fun getPromisesInteractionsDetail(
        promiseId: Int, interactionType: String
    ): Result<PromisesInteractionsDetail> = promisesRemoteDataSource.getPromisesInteractionsDetail(
        promiseId = promiseId, interactionType = interactionType
    ).mapCatching {
        it.interactionsDetail.toDomain()
    }
}