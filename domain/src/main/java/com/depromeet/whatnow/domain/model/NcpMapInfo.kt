package com.depromeet.whatnow.domain.model

data class NcpMapInfo(
    val last_build_date : String,
    val total : Int,
    val start : Int,
    val display : Int,
    val items : List<NcpMapInfoItem>
)