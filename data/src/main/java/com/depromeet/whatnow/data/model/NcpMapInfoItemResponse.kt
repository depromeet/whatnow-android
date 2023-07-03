package com.depromeet.whatnow.data.model

import com.depromeet.whatnow.data.entity.NcpMapInfoItemEntity
import com.depromeet.whatnow.domain.model.NcpMapInfoItem
import com.google.gson.annotations.SerializedName

data class NcpMapInfoItemResponse(
    @SerializedName("title")
    val title : String,
    @SerializedName("link")
    val link : String,
    @SerializedName("category")
    val category : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("telephone")
    val telephone : String,
    @SerializedName("address")
    val address : String,
    @SerializedName("road_address")
    val road_address : String,
    @SerializedName("mapx")
    val mapx : String,
    @SerializedName("mapy")
    val mapy : String
)

fun NcpMapInfoItemResponse.toDomain(): NcpMapInfoItemEntity = NcpMapInfoItemEntity(
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