package com.depromeet.whatnow.domain.repo


import com.depromeet.whatnow.domain.model.Location
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.depromeet.whatnow.domain.model.PromisesMonthlyUserList

interface PromisesRepository {
    suspend fun getLocation(request: Location): Result<NcpMapInfo>

    suspend fun getPromisesMonthlyUsers(year_month: String): Result<PromisesMonthlyUserList>

}