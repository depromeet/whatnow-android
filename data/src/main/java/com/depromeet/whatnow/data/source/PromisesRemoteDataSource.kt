package com.depromeet.whatnow.data.source

import com.depromeet.whatnow.data.entity.promises.LocationRequest
import com.depromeet.whatnow.data.entity.promises.NcpMapInfoResponse
import com.depromeet.whatnow.domain.utils.ApiResult
import javax.inject.Inject

interface PromisesRemoteDataSource {
    suspend fun getLocation(request : LocationRequest): ApiResult<NcpMapInfoResponse>
}

//class PromiseRemoteDataSourceImpl @Inject constructor() : BaseRemoteDataSource(), PromisesRemoteDataSource{
//    override suspend fun getLocation(request: LocationRequest): ApiResult<NcpMapInfoResponse> {
//        val result = safeApiCallNoEmitter {  }
//    }
//
//}