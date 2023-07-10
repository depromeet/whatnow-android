package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.data.entity.NcpMapInfoEntity
import com.depromeet.whatnow.domain.model.NcpMapInfo
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("last_build_date")
    val last_build_date: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("start")
    val start: Int,
    @SerializedName("display")
    val display: Int,
    @SerializedName("items")
    val items: List<NcpMapInfoItemResponse>
)

fun LocationResponse.toDomain() = NcpMapInfo (
    last_build_date = last_build_date,
    total = total,
    start = start,
    display = display,
    items = items.map { it.toDomain() }
)