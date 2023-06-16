package com.depromeet.whatnow.ui.archive

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.depromeet.whatnow.ui.R

enum class ArchivePromiseTileAction(
    @StringRes val textRes: Int,
    @DrawableRes val iconRes: Int
) {
    Share(
        textRes = R.string.share_promise,
        iconRes = R.drawable.ic_share
    ),
    Delete(
        textRes = R.string.delete_promise,
        iconRes = R.drawable.ic_delete
    )
}
