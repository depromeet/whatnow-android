package com.depromeet.whatnow.data.api

import com.depromeet.whatnow.data.model.BaseResponse
import com.depromeet.whatnow.data.model.LocationRequest
import com.depromeet.whatnow.data.model.LocationResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(API.LOCATION.getLocation)
    suspend fun getLocation(
        @Query("location") locationRequest: LocationRequest,
    ): BaseResponse<LocationResponse>
}