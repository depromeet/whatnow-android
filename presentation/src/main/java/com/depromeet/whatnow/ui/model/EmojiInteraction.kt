package com.depromeet.whatnow.ui.model

import androidx.annotation.DrawableRes
import com.depromeet.whatnow.ui.R
import kotlin.random.Random

enum class EmojiInteraction(
    @DrawableRes val iconRes: Int
) {
    Heart(iconRes = R.drawable.ic_heart),
    Poop(iconRes = R.drawable.ic_poop),
    Music(iconRes = R.drawable.ic_music),
    Footprint(iconRes = R.drawable.ic_footprint)
}

fun DUMMY_EMOJI(): Map<EmojiInteraction, Int> =
    EmojiInteraction.values().associateWith {
        if (it == EmojiInteraction.Footprint) 0 else Random.nextInt(1000)
    }