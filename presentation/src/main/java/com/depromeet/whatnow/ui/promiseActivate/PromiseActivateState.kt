package com.depromeet.whatnow.ui.promiseActivate

import com.depromeet.whatnow.ui.model.Promise

data class PromiseActivateState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    val selectedTab: PromiseActivateTab = PromiseActivateTab.Total,
    val totalProfile: List<Promise> = emptyList(),
    val myProfile: List<Promise> = emptyList(),
    val friendProfile: List<Promise> = emptyList()
)