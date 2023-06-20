package com.depromeet.whatnow.ui.promiseActivate

import com.depromeet.whatnow.ui.model.Promise

data class PromiseActivateState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    val selectedTab: PromiseActivateTab = PromiseActivateTab.All,
    val allProfile: List<Promise> = emptyList(),
    val myProfile: List<Promise> = emptyList(),
    val otherProfile: List<Promise> = emptyList()
)