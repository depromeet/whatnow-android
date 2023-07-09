package com.depromeet.whatnow.ui.utils

//    yyyy-MM-dd	“2019-07-04”
//    dd-MMM-yyyy	“04-July-2019”
//    dd/MM/yyyy	“04/07/2019”
//    yyyy-MM-dd'T'HH:mm:ssZ	“2019-07-04T12:30:30+0530”
//    h:mm a	“12:00 PM”
//    yyyy년 MM월 dd일	"2019년 01월 10일"

fun String.toDate(): String {
    val date = this
    val hour = date.substring(11, 13)
    val minute = date.substring(14, 16)
    return if (hour.toInt() >= 12) {
        "오후 ${hour}시 ${minute}분"
    } else {
        "오전 ${hour}시 ${minute}분"
    }
}