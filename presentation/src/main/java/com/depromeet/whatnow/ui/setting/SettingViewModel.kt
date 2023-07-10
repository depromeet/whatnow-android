package com.depromeet.whatnow.ui.setting

import android.graphics.Bitmap
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.ui.model.DUMMY_USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : BaseViewModel() {

    private val _uiState = MutableStateFlow(
        SettingState(
            user = DUMMY_USER(),
            isNotificationAvailable = true
        )
    )
    val uiState: StateFlow<SettingState> = _uiState.asStateFlow()

    fun setNotification(enabled: Boolean) {
        _uiState.update { it.copy(isNotificationAvailable = enabled) }
    }

    fun setEditMode(value: Boolean) {
        _uiState.update { it.copy(isEditMode = value) }
    }

    fun inputNickname(value: String) {
        _uiState.update { it.copy(newNickname = value) }
    }

    fun onPickImage(bitmap: Bitmap) {
        _uiState.update { it.copy(newProfileImage = bitmap) }
    }
}
