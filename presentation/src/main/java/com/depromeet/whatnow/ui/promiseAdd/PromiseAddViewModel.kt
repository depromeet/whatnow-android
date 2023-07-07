package com.depromeet.whatnow.ui.promiseAdd

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.model.Location
import com.depromeet.whatnow.domain.usecase.GetJwtTokenUseCase
import com.depromeet.whatnow.domain.usecase.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PromiseAddViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getJwtTokenUseCase: GetJwtTokenUseCase
) : BaseViewModel() {

    private val itemList = mutableListOf<PromiseAddPlace>()

    init {
        launch {
            Log.d("yw","엑세스 토큰 저장되어있는것 : ${getJwtTokenUseCase().getOrThrow().accessToken}")
            getLocationUseCase("강남")
                .onSuccess { Log.d("yw","it.items = ${it.items}") }
                .onFailure { Log.d("yw","실패 $it") }
        }

    }

    fun getItemList(): List<PromiseAddPlace> {
        return emptyList()
    }
}