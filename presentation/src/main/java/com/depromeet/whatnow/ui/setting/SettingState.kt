package com.depromeet.whatnow.ui.setting

import android.graphics.Bitmap
import com.depromeet.whatnow.ui.model.User

data class SettingState(
    val user: User,
    val isNotificationAvailable: Boolean,
    val isEditMode: Boolean = false,
    val newNickname: String = "",
    val newProfileImage: Bitmap? = null
)
