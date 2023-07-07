package com.depromeet.whatnow.data.model.response

import com.depromeet.whatnow.domain.model.NcpMapInfoItem
import com.google.gson.annotations.SerializedName

data class NcpMapInfoItemResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("telephone")
    val telephone: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("road_address")
    val road_address: String,
    @SerializedName("mapx")
    val mapx: Double,
    @SerializedName("mapy")
    val mapy: Double
)

fun NcpMapInfoItemResponse.toDomain() = NcpMapInfoItem(
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