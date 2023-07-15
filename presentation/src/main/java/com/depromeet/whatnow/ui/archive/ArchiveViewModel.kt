package com.depromeet.whatnow.ui.archive

import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.GetPromisesUsersStatusUseCase
import com.depromeet.whatnow.ui.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(
    private val getPromisesUsersStatusUseCase: GetPromisesUsersStatusUseCase
) : BaseViewModel() {

    private val _uiState = MutableStateFlow(ArchiveState())
    val uiState: StateFlow<ArchiveState> = _uiState.asStateFlow()

    init {
        launch {
            val futurePromises = getPromisesUsersStatusUseCase("BEFORE").getOrThrow()
            val pastPromises = getPromisesUsersStatusUseCase("END").getOrThrow()
            _uiState.update { state ->
                state.copy(
                    futurePromises = futurePromises
                        .map { it.toUiModel() }
                        .sortedByDescending { it.datetime },
                    pastPromises = pastPromises
                        .map { it.toUiModel() }
                        .sortedByDescending { it.datetime }
                )
            }
        }
    }

    fun selectTab(tab: ArchiveTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }
}
