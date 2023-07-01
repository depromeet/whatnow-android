package com.depromeet.whatnow.ui.picture

import com.depromeet.whatnow.ui.model.Promise

data class PictureState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    val allProfile: List<Promise> = emptyList(),
    val myProfile: List<Promise> = emptyList(),
    val otherProfile: List<Promise> = emptyList(),

    val musicEmoji: List<Promise> = emptyList(),
    val poopEmoji: List<Promise> = emptyList(),
    val heartEmoji: List<Promise> = emptyList(),
    val footPrintEmoji: List<Promise> = emptyList(),

    )