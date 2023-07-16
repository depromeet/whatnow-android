package com.depromeet.whatnow.ui.promiseActivate

import android.os.CountDownTimer
import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.model.CoordinateVo
import com.depromeet.whatnow.domain.usecase.GetPromisesInteractionsDetailUseCase
import com.depromeet.whatnow.domain.usecase.GetPromisesInteractionsUseCase
import com.depromeet.whatnow.domain.usecase.GetPromisesUseCase
import com.depromeet.whatnow.domain.usecase.GetPromisesUsersProgressUseCase
import com.depromeet.whatnow.domain.usecase.GetPromisesUsersUseCase
import com.depromeet.whatnow.domain.usecase.PutPromisesUsersLocationUseCase
import com.depromeet.whatnow.ui.home.getTime
import com.depromeet.whatnow.ui.model.DUMMY_PROMISE
import com.depromeet.whatnow.ui.model.DUMMY_USER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class PromiseActivateViewModel @Inject constructor(
    private val getPromisesUseCase: GetPromisesUseCase,
    private val putPromisesUsersLocationUseCase: PutPromisesUsersLocationUseCase,
    private val getPromisesUsersProgressUseCase: GetPromisesUsersProgressUseCase,
    private val getPromisesInteractionsDetailUseCase: GetPromisesInteractionsDetailUseCase,
    private val getPromisesInteractionsUseCase: GetPromisesInteractionsUseCase,
    private val getPromisesUsersUseCase: GetPromisesUsersUseCase,

    ) : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _isClickedMyStatus = MutableStateFlow(false)
    var isClickedMyStatus = _isClickedMyStatus.asStateFlow()

    private val _isClickedMyStatusCategory = MutableStateFlow(false)
    var isClickedMyStatusCategory = _isClickedMyStatusCategory.asStateFlow()

    private val _myStatusCategoryTitle = MutableStateFlow("가는 중")
    var myStatusCategoryTitle = _myStatusCategoryTitle.asStateFlow()

    private val _uiState = MutableStateFlow(PromiseActivateState())
    val uiState: StateFlow<PromiseActivateState> = _uiState.asStateFlow()
    var promiseId = MutableStateFlow<Int>(-1)

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

    fun getPromises() {
        launch {
            getPromisesUseCase(promise_id = promiseId.value).onSuccess {
                _uiState.value.promise.value = it
            }.onFailure {
                Log.d("ttt getPromisesUseCase onFailure", it.toString())
            }
        }
    }

    fun getPromisesUsers() {
//        _uiState.value.promisesUsersStatusList.value = listOf(
//            PromisesUsersStatus(
//                promiseId = 1,
//                mainUserId = 2,
//                userLocation = CoordinateVo(37.516152086, 127.019497385),
//                promiseUserType = "READY",
//                promiseProgress = "SHOWER"
//            )
//        )

        launch {
            getPromisesUsersUseCase(promise_id = promiseId.value).onSuccess {
                _uiState.value.promisesUsersStatusList.value = it
            }.onFailure {
                Log.d("ttt onFailure", it.toString())

            }
        }

    }

    fun putPromisesUsersLocation(userLocation: CoordinateVo) {
        launch {
            putPromisesUsersLocationUseCase(
                promise_id = promiseId.value, userLocation = userLocation
            ).onSuccess {}.onFailure {
                Log.d("ttt", it.toString())

            }
        }

    }

    fun getPromisesUsersProgress(userId: Int) {
        launch {
            getPromisesUsersProgressUseCase(
                promiseId = promiseId.value, userId = userId
            ).onSuccess {
                _uiState.value.promisesProgress = it

            }.onFailure { }
        }
    }

    fun getPromisesInteractionsDetail(interactionType: String) {
        launch {
            getPromisesInteractionsDetailUseCase(
                promiseId = promiseId.value, interactionType = interactionType
            ).onSuccess {
                _uiState.value.interactionsDetail = it.interactions
            }.onFailure {
                Log.d("ttt getPromisesInteractionsDetail onFailure", it.toString())

            }
        }
    }

    fun getPromisesInteractions() {
        launch {
            getPromisesInteractionsUseCase(promiseId = promiseId.value).onSuccess {
                _uiState.value.promisesInteractions = it
            }.onFailure {
                Log.d("ttt getPromisesInteractions onFailure", it.toString())

            }
        }
    }

    fun selectTab(tab: PromiseActivateTab) {
        _uiState.update { it.copy(selectedTab = tab) }
    }

    fun selectEmojiTab(tab: PromiseEmojiTab) {
        _uiState.update { it.copy(selectedEmojiTab = tab) }
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

    fun timerStart(date: String) {

        val deadLine = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"))
        deadLine.add(Calendar.HOUR, date.substring(11, 13).toInt())
        deadLine.add(Calendar.MINUTE, date.substring(14, 16).toInt())

        val diffSec: Long =
            (deadLine.timeInMillis - Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul")).timeInMillis)
        lateinit var mTimer: CountDownTimer

        mTimer = object : CountDownTimer(diffSec, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                launch {
                    _uiState.value.time.value = getTime(deadLine)

                }
            }

            override fun onFinish() {
                // CountDown가 종료될 때
                _uiState.value.isTimeOver.value = true
                mTimer.cancel()
            }
        }

        mTimer.start()
    }
}