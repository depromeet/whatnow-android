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
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPromisesUsersStatusUseCase: GetPromisesUsersStatusUseCase,
    private val getPromisesActiveUseCase: GetPromisesActiveUseCase

) : BaseViewModel() {

    private val _isRefresh = MutableStateFlow(false)
    val isRefresh = _isRefresh.asStateFlow()

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    init {
        getPromisesUsersStatus()
        a()
    }

    fun getPromisesUsersStatus() {
        launch {
            getPromisesUsersStatusUseCase(status = "BEFORE").onSuccess {
                Log.d("ttt onSuccess", "it.items = ${it.contest}")
                _uiState.value.promisesUsersStatus = it.contest
                when (it.contest.first().promiseUsers.first().promiseUserType) {
                    "READY" -> {
                        checkedPromise(it.contest.first().promiseUsers.first().interactions.first().promiseId)
                    }

                    "WAIT" -> _uiState.value.currentStatus = HomeActivateStatus.Wait
                    "LATE" -> _uiState.value.currentStatus = HomeActivateStatus.Late
                    "CANCEL" -> {}
                }
            }.onFailure { Log.d("ttt onFailure", it.toString()) }
        }

    }


    fun checkedPromise(promise_id: Int) {
        launch {
            getPromisesActiveUseCase(promise_id = promise_id).onSuccess {
                if (it) _uiState.value.currentStatus = HomeActivateStatus.Activity
                else _uiState.value.currentStatus = HomeActivateStatus.InActivity
            }.onFailure { }
        }
    }

    fun refresh() {
        _isRefresh.value = true
    }

    fun onRefresh() {
        _isRefresh.value = false
    }

    fun a() {
        val deadLine = Calendar.getInstance()
        deadLine.add(Calendar.DAY_OF_MONTH, 1)
        deadLine.add(Calendar.HOUR, 9)
        deadLine.add(Calendar.MINUTE, 28)

        val strDeadline = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(deadLine.time)
//    mBinding.textDeadline.text = "$strDeadline 까지 남은 시간"

        val diffSec: Long = (deadLine.timeInMillis - Calendar.getInstance().timeInMillis)
        lateinit var mTimer: CountDownTimer

        mTimer = object : CountDownTimer(diffSec, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _uiState.value.test = getTime(deadLine)
            }

            override fun onFinish() {
                // CountDown가 종료될 때
            }
        }

        mTimer.start()
    }
}


private fun getTime(deadLine: Calendar): List<String> {

    val diffSec: Long = (deadLine.timeInMillis - Calendar.getInstance().timeInMillis) / 1000

    val hourTime = Math.floor((diffSec / 3600).toDouble()).toInt()
    val minTime = Math.floor(((diffSec - 3600 * hourTime) / 60).toDouble()).toInt()
    val secTime = Math.floor((diffSec - 3600 * hourTime - 60 * minTime).toDouble()).toInt()

    if (hourTime <= 0 && minTime <= 0 && secTime <= 0)
        return listOf("0", "0")

    val hour = String.format("%02d", hourTime)
    val min = String.format("%02d", minTime)
    val sec = String.format("%02d", secTime)

    return listOf(hour, min)
}