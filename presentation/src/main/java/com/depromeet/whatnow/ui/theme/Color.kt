package com.depromeet.whatnow.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.graphics.Color

val PrimaryBlack = Color(0xFF1F2128)
val PrimaryYellow = Color(0xFFFFF6A1)
val SecondaryYellow = Color(0xFFFFF06B)
val SecondaryPurple = Color(0xFF6568EB)

val Description = Color(0xFF0065FF)
val Error = Color(0xFFFF4747)

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
    primary = PrimaryBlack,
    onPrimary = White,
    secondary = PrimaryYellow,
    onSecondary = Gray900,
    error = Error,
    onError = White,
    background = White,
    surface = White
)

@Stable
class WhatNowColors(
    primaryBlack: Color,
    primaryYellow: Color,
    secondaryYellow: Color,
    secondaryPurple: Color,
    description: Color,
    error: Color,
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
    var primaryBlack: Color by mutableStateOf(primaryBlack, structuralEqualityPolicy())
        private set
    var primaryYellow: Color by mutableStateOf(primaryYellow, structuralEqualityPolicy())
        private set
    var secondaryYellow: Color by mutableStateOf(secondaryYellow, structuralEqualityPolicy())
        private set
    var secondaryPurple: Color by mutableStateOf(secondaryPurple, structuralEqualityPolicy())
        private set
    var description: Color by mutableStateOf(description, structuralEqualityPolicy())
        private set
    var error: Color by mutableStateOf(error, structuralEqualityPolicy())
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
        primaryBlack: Color = this.primaryBlack,
        primaryYellow: Color = this.primaryYellow,
        secondaryYellow: Color = this.secondaryYellow,
        secondaryPurple: Color = this.secondaryPurple,
        description: Color = this.description,
        error: Color = this.error,
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
        primaryBlack = primaryBlack,
        primaryYellow = primaryYellow,
        secondaryYellow = secondaryYellow,
        secondaryPurple = secondaryPurple,
        description = description,
        error = error,
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
    primaryBlack: Color = PrimaryBlack,
    primaryYellow: Color = PrimaryYellow,
    secondaryYellow: Color = SecondaryYellow,
    secondaryPurple: Color = SecondaryPurple,
    description: Color = Description,
    error: Color = Error,
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
    primaryBlack = primaryBlack,
    primaryYellow = primaryYellow,
    secondaryYellow = secondaryYellow,
    secondaryPurple = secondaryPurple,
    description = description,
    error = error,
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
