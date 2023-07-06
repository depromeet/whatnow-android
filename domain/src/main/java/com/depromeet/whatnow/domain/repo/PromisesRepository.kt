package com.depromeet.whatnow.domain.repo


import com.depromeet.whatnow.domain.model.GetPromisesProgressList
import com.depromeet.whatnow.domain.model.GetPromisesUsersStatusList
import com.depromeet.whatnow.domain.model.Location
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList
import com.depromeet.whatnow.domain.model.PromisesProgress

interface PromisesRepository {
    suspend fun getLocation(request: Location): Result<NcpMapInfo>

    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUserList>

    suspend fun getPromisesUsersStatus(status: String): Result<GetPromisesUsersStatusList>

    suspend fun patchPromisesProgress(
        progressCode: String,
        promiseId: Int
    ): Result<PromisesProgress>

    suspend fun getPromisesUsersProgress(
        promiseId: Int,
        userId: Int
    ): Result<PromisesProgress>

    suspend fun getPromisesProgress(): Result<GetPromisesProgressList>

    suspend fun postUsersMeImageSuccess(imageKey: String): Unit

}