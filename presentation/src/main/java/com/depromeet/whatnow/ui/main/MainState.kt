package com.depromeet.whatnow.ui.main

import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus

data class MainState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    var promisesUsersStatus: List<GetPromisesUsersStatus> = emptyList()
)