package com.depromeet.whatnow.ui.setting

import android.content.Context
import android.graphics.Bitmap
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.GetImagePresignedUrlUseCase
import com.depromeet.whatnow.domain.usecase.GetUsersMeUseCase
import com.depromeet.whatnow.domain.usecase.LogoutUseCase
import com.depromeet.whatnow.domain.usecase.PatchUsersProfileUseCase
import com.depromeet.whatnow.domain.usecase.PostImagePresignedUrlUseCase
import com.depromeet.whatnow.domain.usecase.PostImageUsersMeUseCase
import com.depromeet.whatnow.domain.usecase.WithdrawUseCase
import com.depromeet.whatnow.ui.model.User
import com.depromeet.whatnow.ui.model.toUiModel
import com.depromeet.whatnow.ui.utils.BitmapUtil.toFile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val getUsersMeUseCase: GetUsersMeUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val withdrawUseCase: WithdrawUseCase,
    private val postImagePresignedUrlUseCase: PostImagePresignedUrlUseCase,
    private val postImageUsersMeUseCase: PostImageUsersMeUseCase,
    private val getImagePresignedUrlUseCase: GetImagePresignedUrlUseCase,
    private val patchUsersProfileUseCase: PatchUsersProfileUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(
        SettingState(
            user = User.INIT,
            isNotificationAvailable = true
        )
    )
    val uiState: StateFlow<SettingState> = _uiState.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        launch {
            val user = getUsersMeUseCase().getOrThrow()
            _uiState.update { it.copy(user = user.toUiModel(), newNickname = user.nickname) }
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

    fun logout() = launch { logoutUseCase() }

    fun withdraw() = launch { withdrawUseCase() }

    fun updateProfile(context: Context) {
        launch {
            val file = _uiState.value.newProfileImage?.toFile(
                context = context,
                name = LocalDateTime.now().toString()
            )

            val presignedUrlData = getImagePresignedUrlUseCase("PNG").getOrThrow()
            val url = presignedUrlData.presignedUrl
            val imageKey = presignedUrlData.key

            val profileImageUrl: String = file?.let {
                postImagePresignedUrlUseCase(url, file).getOrThrow()
                postImageUsersMeUseCase(
                    imageKey = imageKey,
                    extension = "PNG"
                ).getOrThrow().imageUrl
            } ?: ""

            patchUsersProfileUseCase(
                _uiState.value.newNickname,
                profileImageUrl,
                imageKey,
                profileImageUrl.isBlank()
            ).getOrThrow()
        }
    }
}
