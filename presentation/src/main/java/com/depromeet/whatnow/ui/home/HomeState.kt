package com.depromeet.whatnow.ui.home

import com.depromeet.whatnow.domain.model.GetPromisesUsersStatus

data class HomeState(
    val showTooltip: Boolean = false,
    val isAudioPlaying: Boolean = false,
    var currentStatus: HomeActivateStatus = HomeActivateStatus.InActivity,
    var promisesUsersStatus: List<GetPromisesUsersStatus> = emptyList(),
    var test: List<String> = emptyList()
)