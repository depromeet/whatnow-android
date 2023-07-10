package com.depromeet.whatnow.ui.home

import android.os.CountDownTimer
import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.GetPromisesActiveUseCase
import com.depromeet.whatnow.domain.usecase.GetPromisesUsersStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPromisesUsersStatusUseCase: GetPromisesUsersStatusUseCase,
    private val getPromisesActiveUseCase: GetPromisesActiveUseCase,

    ) : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        getPromisesUsersStatus()
    }

    fun getPromisesUsersStatus() {
        launch {
//            _uiState.value.currentStatus = HomeActivateStatus.Activity
//            _uiState.value.promisesUsersStatus = listOf(
//                GetPromisesUsersStatus(
//                    promiseId = 0,
//                    address = "string",
//                    coordinateVo = CoordinateVo(37.566535, 126.9779692),
//                    title = "string",
//                    endTime = "2023-07-07T14:57:18.474Z",
//                    promiseUsers = listOf(
//                        PromiseUsers(
//                            profileImg = "string",
//                            nickname = "string",
//                            isDefaultImg = true,
//                            promiseUserType = "READY",
//                            interactions = listOf(Interactions(0, 0, "Music", 0))
//                        )
//                    ),
//                    promiseImageUrls = listOf("string"),
//                    timeOverLocations = listOf(
//                        TimeOverLocations(
//                            0,
//                            CoordinateVo(37.566535, 126.9779692)
//                        )
//                    )
//                )
//            )
            timerStart("2023-07-07T14:57:18.474Z")

            getPromisesUsersStatusUseCase(status = "BEFORE").onSuccess {
                Log.d("ttt onSuccess", "it.items = ${it}")
//                _uiState.value.promisesUsersStatus = it
                if (it.isEmpty()) {
                    _uiState.value.currentStatus = HomeActivateStatus.InActivity
                } else {
                    when (it.first().promiseUsers.first().promiseUserType) {
                        "READY" -> {
                            checkedPromise(
                                it.first().promiseUsers.first().interactions.first().promiseId,
                                it.first().endTime
                            )
                        }

                        "WAIT" -> _uiState.value.currentStatus = HomeActivateStatus.Wait
                        "LATE" -> _uiState.value.currentStatus = HomeActivateStatus.Late
                        "CANCEL" -> {}
                    }
                }

            }.onFailure { Log.d("ttt onFailure", it.toString()) }
        }

    }


    fun checkedPromise(promise_id: Int, date: String) {
        launch {
            getPromisesActiveUseCase(promise_id = promise_id).onSuccess {
                if (it) {
                    _uiState.value.currentStatus = HomeActivateStatus.Activity

                    timerStart(date)
                } else _uiState.value.currentStatus = HomeActivateStatus.InActivity
            }.onFailure { }
        }
    }

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }

    fun timerStart(date: String) {

        val deadLine = Calendar.getInstance()
        deadLine.add(Calendar.HOUR, date.substring(11, 13).toInt())
        deadLine.add(Calendar.MINUTE, date.substring(14, 16).toInt())

        val diffSec: Long = (deadLine.timeInMillis - Calendar.getInstance().timeInMillis)
        lateinit var mTimer: CountDownTimer

        mTimer = object : CountDownTimer(diffSec, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                launch {
                    _uiState.value.timeOver.value = getTime(deadLine)

                }
            }

            override fun onFinish() {
                // CountDown가 종료될 때
            }
        }

        mTimer.start()
    }
}


fun getTime(deadLine: Calendar): Pair<String, String> {

    val diffSec: Long = (deadLine.timeInMillis - Calendar.getInstance().timeInMillis) / 1000

    val hourTime = Math.floor((diffSec / 3600).toDouble()).toInt()
    val minTime = Math.floor(((diffSec - 3600 * hourTime) / 60).toDouble()).toInt()
    val secTime = Math.floor((diffSec - 3600 * hourTime - 60 * minTime).toDouble()).toInt()

    if (hourTime <= 0 && minTime <= 0 && secTime <= 0) return Pair("0", "0")

    val hour = String.format("%02d", hourTime)
    val min = String.format("%02d", minTime)
    val sec = String.format("%02d", secTime)

    return Pair(min, sec)
}