package com.depromeet.whatnow.ui.promiseAdd

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.model.NcpMapInfoItem
import com.depromeet.whatnow.domain.model.Promise
import com.depromeet.whatnow.domain.usecase.GetJwtTokenUseCase
import com.depromeet.whatnow.domain.usecase.GetLocationUseCase
import com.depromeet.whatnow.domain.usecase.GetUsersMeUseCase
import com.depromeet.whatnow.domain.usecase.PostPromisesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PromiseAddViewModel @Inject constructor(
    private val getUsersMeUseCase: GetUsersMeUseCase,
    private val postPromisesUseCase: PostPromisesUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val getJwtTokenUseCase: GetJwtTokenUseCase
) : BaseViewModel() {

    private val itemList = mutableListOf<PromiseAddPlace>()
    private var locationList: ArrayList<PromiseAddPlace> = ArrayList()

    private val _uiState = MutableStateFlow<PromiseAddState>(PromiseAddState.MakePromise)
    val uiState: StateFlow<PromiseAddState> = _uiState.asStateFlow()

    private val _locationListUi = MutableStateFlow(false)
    val locationListUi: StateFlow<Boolean> = _locationListUi.asStateFlow()

    private val _locationListData = MutableStateFlow(listOf(PromiseAddPlace("", "",0.0,0.0)))
    val locationListData: StateFlow<List<PromiseAddPlace>> = _locationListData.asStateFlow()

    private val _promiseData = MutableStateFlow()

    private val _selectedCalendar = MutableStateFlow("")
    val selectedCalendar: StateFlow<String> = _selectedCalendar.asStateFlow()

    private val _selectedTime = MutableStateFlow("")
    val selectedTime: StateFlow<String> = _selectedTime.asStateFlow()

    private val _selectedPlace = MutableStateFlow("")
    val selectedPlace: StateFlow<String> = _selectedPlace.asStateFlow()


    init {

        launch {
            Log.d("yw", "엑세스 토큰 저장되어있는것 : ${getJwtTokenUseCase().getOrThrow().accessToken}")
        }


    }

    fun getPromiseDetail(calendar: String, time:String, place:String, latitude: Double, longitude: Double){
        launch {
            getUsersMeUseCase()
            postPromisesUseCase(Promise(title = calendar, ))
        }
        _uiState.value = PromiseAddState.DetailPromise
        _selectedCalendar.update { calendar }
        _selectedTime.update { time }
        _selectedPlace.update { place }
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
                        locationList.add(PromiseAddPlace(x.title, x.address,x.mapx,x.mapy))
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