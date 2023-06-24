package com.depromeet.whatnow.ui.archive.detail

import com.depromeet.whatnow.ui.model.DUMMY_PROMISE
import com.depromeet.whatnow.ui.model.Promise

data class DetailState(
    val isLoading: Boolean = false,
    val selectedPromise: Promise = DUMMY_PROMISE()
)
