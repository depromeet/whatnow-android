package com.depromeet.whatnow.data.entity

import com.depromeet.whatnow.domain.model.NcpMapInfoItem

data class NcpMapInfoItemEntity(
    val title : String,
    val link : String,
    val category : String,
    val description : String,
    val telephone : String,
    val address : String,
    val road_address : String,
    val mapx : Double,
    val mapy : Double
)

fun NcpMapInfoItemEntity.toDomain() : NcpMapInfoItem = NcpMapInfoItem(
    title = title,
    link = link,
    category = category,
    description = description,
    telephone = telephone,
    address = address,
    road_address = road_address,
    mapx = mapx,
    mapy = mapy
)