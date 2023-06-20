package com.depromeet.whatnow.ui.model

import androidx.annotation.DrawableRes
import com.depromeet.whatnow.ui.R

enum class HighlightType(
    @DrawableRes imageRes: Int
) {
    Meet(imageRes = R.drawable.img_highlight_meet),
    Arrive(imageRes = R.drawable.img_highlight_arrive)
}
