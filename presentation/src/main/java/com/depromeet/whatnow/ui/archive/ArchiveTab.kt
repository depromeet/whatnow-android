package com.depromeet.whatnow.ui.archive

import androidx.annotation.StringRes
import com.depromeet.whatnow.ui.R

enum class ArchiveTab(
    @StringRes stringRes: Int
) {
    Future(R.string.future_promise),
    Past(R.string.past_promise)
}
