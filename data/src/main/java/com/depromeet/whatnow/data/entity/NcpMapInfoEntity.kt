package com.depromeet.whatnow.data.entity

import com.depromeet.whatnow.domain.model.NcpMapInfo

data class NcpMapInfoEntity(
    val last_build_date : String,
    val total : Int,
    val start : Int,
    val display : Int,
    val items : List<NcpMapInfoItemEntity>
)

internal fun NcpMapInfoEntity.toDomain() = NcpMapInfo(
    last_build_date = last_build_date,
    total = total,
    start = start,
    display = display,
    items = items.map { it.toDomain() }
)