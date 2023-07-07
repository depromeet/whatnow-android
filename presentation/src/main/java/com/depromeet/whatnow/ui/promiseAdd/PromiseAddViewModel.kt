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
    getLocationUseCase: GetLocationUseCase,
    private val getJwtTokenUseCase: GetJwtTokenUseCase
) : BaseViewModel() {

    private val itemList = mutableListOf<PromiseAddPlace>()

    init {
        launch {
            Log.d("yw","엑세스 토큰 저장되어있는것 : ${getJwtTokenUseCase().getOrThrow().accessToken}")
            val asd = Location(location = "강남")
            getLocationUseCase(asd)
                .onSuccess { Log.d("yw","it.items = ${it.items}") }
                .onFailure { Log.d("yw","실패 $it") }
        }

    }

    fun getItemList(): List<PromiseAddPlace> {
        return emptyList()
    }
}