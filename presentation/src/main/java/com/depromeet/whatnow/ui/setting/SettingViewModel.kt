package com.depromeet.whatnow.ui.setting

import android.graphics.Bitmap
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.GetUsersMeUseCase
import com.depromeet.whatnow.ui.model.User
import com.depromeet.whatnow.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getUsersMeUseCase: GetUsersMeUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(
        SettingState(
            user = User.INIT,
            isNotificationAvailable = true
        )
    )
    val uiState: StateFlow<SettingState> = _uiState.asStateFlow()

    init {
        launch {
            val user = getUsersMeUseCase().getOrThrow()
            _uiState.update { it.copy(user = user.toUiModel()) }
        }
    }

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
