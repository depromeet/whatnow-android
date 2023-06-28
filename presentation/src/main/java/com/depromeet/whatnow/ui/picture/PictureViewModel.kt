package com.depromeet.whatnow.ui.picture

import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.ui.model.DUMMY_PROMISE
import com.depromeet.whatnow.ui.model.DUMMY_USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PictureViewModel @Inject constructor() : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _isClickedMyStatus = MutableStateFlow(false)
    var isClickedMyStatus = _isClickedMyStatus.asStateFlow()

    private val _isClickedMyStatusCategory = MutableStateFlow(false)
    var isClickedMyStatusCategory = _isClickedMyStatusCategory.asStateFlow()

    private val _myStatusCategoryTitle = MutableStateFlow("가는 중")
    var myStatusCategoryTitle = _myStatusCategoryTitle.asStateFlow()

    private val _uiState = MutableStateFlow(PictureState())
    val uiState: StateFlow<PictureState> = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(
                allProfile = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), myProfile = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), otherProfile = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ),

                musicEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), poopEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), heartEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                ), footPrintEmoji = listOf(
                    DUMMY_PROMISE(participants = List(6) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(2) { DUMMY_USER() }),
                    DUMMY_PROMISE(participants = List(1) { DUMMY_USER() })
                )
            )
        }
    }

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }

    fun onClickedMyStatus() {
        _isClickedMyStatus.value = !_isClickedMyStatus.value
    }

    fun onClickedMyStatusCategory() {
        _isClickedMyStatusCategory.value = !_isClickedMyStatusCategory.value
    }

    fun onMyStatusCategoryChange(category: String) {
        _myStatusCategoryTitle.value = category
        onClickedMyStatusCategory()
    }
}