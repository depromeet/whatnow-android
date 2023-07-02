package com.depromeet.whatnow.domain.repo


import com.depromeet.whatnow.domain.model.Location
import com.depromeet.whatnow.domain.model.NcpMapInfo

interface PromisesRepository {
    suspend fun getLocation(request : Location): Result<NcpMapInfo>
}