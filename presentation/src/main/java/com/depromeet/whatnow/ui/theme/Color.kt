package com.depromeet.whatnow.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

val WhatNowBlack = Color(0xFF1F2128)
val WhatNowPurple = Color(0xFF6568EB)
val WhatNowYellow = Color(0xFFFFF06B)
val WhatNowWhite = Color(0xFFF9F9F9)
val WhatNowError = Color(0xFFFF4747)

val Gray900 = Color(0xFF1C1C1C)
val Gray800 = Color(0xFF3C3C3C)
val Gray700 = Color(0xFF5B5B5B)
val Gray600 = Color(0xFF6E6E6E)
val Gray500 = Color(0xFF979797)
val Gray400 = Color(0xFFB7B7B7)
val Gray300 = Color(0xFFDADADA)
val Gray200 = Color(0xFFEAEAEA)
val Gray100 = Color(0xFFF3F3F3)
val Gray50 = Color(0xFFF9F9F9)

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

val MaterialColors = lightColorScheme(
    primary = WhatNowBlack,
    onPrimary = White,
    secondary = WhatNowPurple,
    onSecondary = Gray900,
    tertiary = WhatNowYellow,
    onTertiary = Black,
    error = WhatNowError,
    onError = White,
    background = WhatNowWhite,
    surface = WhatNowWhite
)

@Stable
class WhatNowColors(
    whatNowBlack: Color,
    whatNowPurple: Color,
    whatNowYellow: Color,
    whatNowError: Color,
    gray900: Color,
    gray800: Color,
    gray700: Color,
    gray600: Color,
    gray500: Color,
    gray400: Color,
    gray300: Color,
    gray200: Color,
    gray100: Color,
    gray50: Color
) {
    var whatNowBlack: Color by mutableStateOf(whatNowBlack, structuralEqualityPolicy())
        private set
    var whatNowPurple: Color by mutableStateOf(whatNowPurple, structuralEqualityPolicy())
        private set
    var whatNowYellow: Color by mutableStateOf(whatNowYellow, structuralEqualityPolicy())
        private set
    var whatNowError: Color by mutableStateOf(whatNowError, structuralEqualityPolicy())
        private set
    var gray900: Color by mutableStateOf(gray900, structuralEqualityPolicy())
        private set
    var gray800: Color by mutableStateOf(gray800, structuralEqualityPolicy())
        private set
    var gray700: Color by mutableStateOf(gray700, structuralEqualityPolicy())
        private set
    var gray600: Color by mutableStateOf(gray600, structuralEqualityPolicy())
        private set
    var gray500: Color by mutableStateOf(gray500, structuralEqualityPolicy())
        private set
    var gray400: Color by mutableStateOf(gray400, structuralEqualityPolicy())
        private set
    var gray300: Color by mutableStateOf(gray300, structuralEqualityPolicy())
        private set
    var gray200: Color by mutableStateOf(gray200, structuralEqualityPolicy())
        private set
    var gray100: Color by mutableStateOf(gray100, structuralEqualityPolicy())
        private set
    var gray50: Color by mutableStateOf(gray50, structuralEqualityPolicy())
        private set

    fun copy(
        whatNowBlack: Color = this.whatNowBlack,
        whatNowPurple: Color = this.whatNowPurple,
        whatNowYellow: Color = this.whatNowYellow,
        whatNowError: Color = this.whatNowError,
        gray900: Color = this.gray900,
        gray800: Color = this.gray800,
        gray700: Color = this.gray700,
        gray600: Color = this.gray600,
        gray500: Color = this.gray500,
        gray400: Color = this.gray400,
        gray300: Color = this.gray300,
        gray200: Color = this.gray200,
        gray100: Color = this.gray100,
        gray50: Color = this.gray50
    ) = WhatNowColors(
        whatNowBlack = whatNowBlack,
        whatNowPurple = whatNowPurple,
        whatNowYellow = whatNowYellow,
        whatNowError = whatNowError,
        gray900 = gray900,
        gray800 = gray800,
        gray700 = gray700,
        gray600 = gray600,
        gray500 = gray500,
        gray400 = gray400,
        gray300 = gray300,
        gray200 = gray200,
        gray100 = gray100,
        gray50 = gray50
    )
}

fun lightColors(
    whatNowBlack: Color = WhatNowBlack,
    whatNowPurple: Color = WhatNowPurple,
    whatNowYellow: Color = WhatNowYellow,
    whatNowError: Color = WhatNowError,
    gray900: Color = Gray900,
    gray800: Color = Gray800,
    gray700: Color = Gray700,
    gray600: Color = Gray600,
    gray500: Color = Gray500,
    gray400: Color = Gray400,
    gray300: Color = Gray300,
    gray200: Color = Gray200,
    gray100: Color = Gray100,
    gray50: Color = Gray50
) = WhatNowColors(
    whatNowBlack = whatNowBlack,
    whatNowPurple = whatNowPurple,
    whatNowYellow = whatNowYellow,
    whatNowError = whatNowError,
    gray900 = gray900,
    gray800 = gray800,
    gray700 = gray700,
    gray600 = gray600,
    gray500 = gray500,
    gray400 = gray400,
    gray300 = gray300,
    gray200 = gray200,
    gray100 = gray100,
    gray50 = gray50
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }
