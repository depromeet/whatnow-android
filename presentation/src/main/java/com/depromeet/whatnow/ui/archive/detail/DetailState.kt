package com.depromeet.whatnow.ui.archive.detail

import com.depromeet.whatnow.ui.model.Promise

data class DetailState(
    val isLoading: Boolean = false,
    val promises: List<Promise>,
    val currentIndex: Int
)
