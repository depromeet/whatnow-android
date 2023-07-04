package com.depromeet.whatnow.ui.splashlogin

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.depromeet.whatnow.ui.R

data class PageItem(
    val index: Int,
    @DrawableRes
    val image: Int,
    @StringRes
    val titleMsg: Int,
    @StringRes
    val description: Int,
)

internal val PageItems = listOf(
    PageItem(
        index = 0,
        image = R.drawable.splash_1,
        titleMsg = R.string.splash_item1_title,
        description = R.string.splash_item1_description,
    ),
    PageItem(
        index = 1,
        image = R.drawable.splash_2,
        titleMsg = R.string.splash_item2_title,
        description = R.string.splash_item2_description,
    ),
    PageItem(
        index = 2,
        image = R.drawable.splash_3,
        titleMsg = R.string.splash_item3_title,
        description = R.string.splash_item3_description,
    ),
)
