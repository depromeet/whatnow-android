package com.depromeet.whatnow.domain.model

data class NcpMapInfoItem(
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