package com.depromeet.whatnow.data.entity.promises

data class NcpMapInfoResponse(
    val last_build_date : String,
    val total : Int,
    val start : Int,
    val display : Int,
    val items : List<NcpMapInfoItem>
)
