package com.depromeet.whatnow.ui.archive.highlight

import com.depromeet.whatnow.ui.model.Highlight

data class HighlightState(
    val isLoading: Boolean = false,
    val highlights: List<Highlight>
)
