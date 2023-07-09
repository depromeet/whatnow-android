package com.depromeet.whatnow.ui.promiseAdd

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.model.NcpMapInfoItem
import com.depromeet.whatnow.domain.usecase.GetJwtTokenUseCase
import com.depromeet.whatnow.domain.usecase.GetLocationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PromiseAddViewModel @Inject constructor(
    private val getLocationUseCase: GetLocationUseCase,
    private val getJwtTokenUseCase: GetJwtTokenUseCase
) : BaseViewModel() {

    private val itemList = mutableListOf<PromiseAddPlace>()
    private var locationList: ArrayList<PromiseAddPlace> = ArrayList()

    private val _locationListUi = MutableStateFlow(false)
    val locationListUi: StateFlow<Boolean> = _locationListUi.asStateFlow()

    private val _locationListData = MutableStateFlow(listOf(PromiseAddPlace("", "")))
    val locationListData: StateFlow<List<PromiseAddPlace>> = _locationListData.asStateFlow()

    init {

        launch {
            Log.d("yw", "엑세스 토큰 저장되어있는것 : ${getJwtTokenUseCase().getOrThrow().accessToken}")
        }


    }


    fun getItemList(location: String) {
        launch {
            Log.d("yw", "getItemList 호출")
            val mapInfoItemList: List<NcpMapInfoItem>
            getLocationUseCase(location)
                .onSuccess {
                    Log.d("yw", "장소 검색 성공 ${it.items}")
                    mapInfoItemList = it.items
                    locationList.clear()
                    _locationListUi.value = false

                    for (x in mapInfoItemList) {
                        locationList.add(PromiseAddPlace(x.title, x.address))
                    }

                    _locationListData.update {
                        locationList.toList()
                    }
                    _locationListUi.value = true

                    Log.d("yw", "_locationListData = ${_locationListData.value}")
                }
                .onFailure { Log.d("yw", "실패 $it") }
        }
    }

}