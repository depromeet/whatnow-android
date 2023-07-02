package com.depromeet.whatnow.ui.home

data class HomeState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    val currentStatus: HomeActivateStatus = HomeActivateStatus.Wait,
)