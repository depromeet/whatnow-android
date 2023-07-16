package com.depromeet.whatnow.ui.promiseAdd

import android.util.Log
import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.model.CoordinateVo
import com.depromeet.whatnow.domain.model.NcpMapInfoItem
import com.depromeet.whatnow.domain.model.PlaceVo
import com.depromeet.whatnow.domain.model.Promise
import com.depromeet.whatnow.domain.usecase.GetJwtTokenUseCase
import com.depromeet.whatnow.domain.usecase.GetLocationUseCase
import com.depromeet.whatnow.domain.usecase.GetUsersMeUseCase
import com.depromeet.whatnow.domain.usecase.PostPromisesUseCase
import com.depromeet.whatnow.domain.usecase.PostPromisesUsersCreate
import com.naver.maps.geometry.Tm128
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
    private val getJwtTokenUseCase: GetJwtTokenUseCase,
    private val postPromisesUsersCreate: PostPromisesUsersCreate
) : BaseViewModel() {

    private var locationList: ArrayList<PromiseAddPlace> = ArrayList()
    private var userId: Int = 0

    private val _locationMap = MutableStateFlow(false)
    val locationMap: StateFlow<Boolean> = _locationMap.asStateFlow()

    private val _locationMapData = MutableStateFlow(MapData(337520.0, 561931.0))
    val locationMapData: StateFlow<MapData> = _locationMapData.asStateFlow()

    private val _uiState = MutableStateFlow<PromiseAddState>(PromiseAddState.MakePromise)
    val uiState: StateFlow<PromiseAddState> = _uiState.asStateFlow()

    private val _locationListUi = MutableStateFlow(false)
    val locationListUi: StateFlow<Boolean> = _locationListUi.asStateFlow()

    private val _locationListData = MutableStateFlow(listOf(PromiseAddPlace("", "", 0.0, 0.0)))
    val locationListData: StateFlow<List<PromiseAddPlace>> = _locationListData.asStateFlow()

    private val _selectedCalendar = MutableStateFlow("")
    val selectedCalendar: StateFlow<String> = _selectedCalendar.asStateFlow()

    private val _selectedTime = MutableStateFlow("")
    val selectedTime: StateFlow<String> = _selectedTime.asStateFlow()

    private val _selectedPlace = MutableStateFlow("")
    val selectedPlace: StateFlow<String> = _selectedPlace.asStateFlow()

    private val _promiseResetPopup = MutableStateFlow(false)
    val promiseResetPopup: StateFlow<Boolean> = _promiseResetPopup.asStateFlow()

    init {
        launch {
            getUsersMeUseCase().onSuccess {
                userId = it.id
            }
            Log.d("yw", "엑세스 토큰 저장되어있는것 : ${getJwtTokenUseCase().getOrThrow().accessToken}")
        }


    }

    fun testASD(a: String, b: Int, c: CoordinateVo = CoordinateVo(0.0, 0.0)) {
        launch {
            postPromisesUsersCreate(a, b, c).onSuccess {
                Log.d("yw", "성공 $it")
            }
                .onFailure {
                    Log.d("yw", "실패 $it")
                }
        }
    }

    fun getTurnOffLocationMap() {
        _locationMap.value = false
    }

    fun getLocationMap(latitude: Double, longitude: Double) {
        val latLng = Tm128(latitude, longitude).toLatLng()
        val mapData = MapData(latitude = latLng.latitude, longitude = latLng.longitude)

        _locationMapData.update {
            mapData
        }
        _locationMap.value = true
    }

    fun promiseReset(turnOnOff: Boolean) {
        _promiseResetPopup.value = turnOnOff
    }

    fun getPromiseDetail(
        screenCalendar: String,
        screenTime: String,
        calendar: String,
        time: String,
        place: String,
        latitude: Double,
        longitude: Double,
    ) {
        launch {
            val endTime = calendar + "T" + time
            val title = "$calendar $time 약속"
            val latLng = Tm128(latitude, longitude).toLatLng()
            val mapData = MapData(latitude = latLng.latitude, longitude = latLng.longitude)

            postPromisesUseCase(
                Promise(
                    title = title, mainUserId = userId, meetPlace = PlaceVo(
                        CoordinateVo(latitude = mapData.latitude, longitude = mapData.longitude),
                        address = place
                    ), endTime = endTime
                )
            ).onSuccess { Log.d("yw", "약속 만들기 성공 $it") }
                .onFailure { Log.d("yw", "약속 만들기 실패 $it") }
        }
        _uiState.value = PromiseAddState.DetailPromise
        _selectedCalendar.update { screenCalendar }
        _selectedTime.update { screenTime }
        _selectedPlace.update { place }
    }

    fun turnOffLocationListUi() {
        _locationListUi.value = false
    }

    fun getItemList(location: String) {
        launch {
            Log.d("yw", "getItemList 호출")
            val mapInfoItemList: List<NcpMapInfoItem>
            if (location != "") {
                getLocationUseCase(location)
                    .onSuccess {
                        mapInfoItemList = it.items
                        locationList.clear()

                        for (x in mapInfoItemList) {
                            locationList.add(PromiseAddPlace(x.title, x.address, x.mapx, x.mapy))
                        }

                        _locationListData.update {
                            locationList.toList()
                        }

                        _locationListUi.value = true
                    }
                    .onFailure { _locationListUi.value = false }
            } else {
                _locationListUi.value = false
            }
        }
    }

}