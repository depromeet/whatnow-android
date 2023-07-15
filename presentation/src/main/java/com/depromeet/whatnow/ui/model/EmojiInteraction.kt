package com.depromeet.whatnow.ui.model

import androidx.annotation.DrawableRes
import com.depromeet.whatnow.ui.R
import kotlin.random.Random

enum class EmojiInteraction(
    @DrawableRes val iconRes: Int
) {
    HEART(iconRes = R.drawable.ic_heart),
    POOP(iconRes = R.drawable.ic_poop),
    MUSIC(iconRes = R.drawable.ic_music),
    STEP(iconRes = R.drawable.ic_footprint)
}

fun DUMMY_EMOJI(): Map<EmojiInteraction, Int> =
    EmojiInteraction.values().associateWith {
        if (it == EmojiInteraction.STEP) 0 else Random.nextInt(1000)
    }