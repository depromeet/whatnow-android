package com.depromeet.whatnow.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.depromeet.whatnow.ui.R

private val NotoSansKR = FontFamily(
    Font(R.font.noto_sans_kr_bold, FontWeight.Bold),
    Font(R.font.noto_sans_kr_medium, FontWeight.Medium),
    Font(R.font.noto_sans_kr_regular, FontWeight.Normal)
)

val Roboto = FontFamily(
    Font(R.font.roboto_bold, FontWeight.Bold),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_regular, FontWeight.Normal)
)

private val Typography = WhatNowTypography(
    headline1 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 36.sp
    ),
    headline2 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp,
        lineHeight = 33.sp
    ),
    headline3 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 30.sp
    ),
    body1 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 27.sp
    ),
    body2 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    body3 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    body4 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
    caption1 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    caption2 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp
    ),
    caption3 = TextStyle(
        fontFamily = NotoSansKR,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp
    ),
)

@Immutable
data class WhatNowTypography(
    val headline1: TextStyle,
    val headline2: TextStyle,
    val headline3: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val body4: TextStyle,
    val caption1: TextStyle,
    val caption2: TextStyle,
    val caption3: TextStyle,
)

internal val LocalTypography = staticCompositionLocalOf { Typography }
