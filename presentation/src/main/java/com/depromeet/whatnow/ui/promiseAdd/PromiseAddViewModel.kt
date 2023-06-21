package com.depromeet.whatnow.ui.promiseAdd

import com.depromeet.whatnow.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PromiseAddViewModel @Inject constructor() : BaseViewModel() {

    private val _placeData = MutableStateFlow(PromiseAddPlaceList())
    val placeData: StateFlow<PromiseAddPlaceList> = _placeData.asStateFlow()

    init {
        _placeData.update {
            it.copy(
                placeList = listOf(
                    DUMMY_PLACE("강남역", "11111"),
                    DUMMY_PLACE("서초역", "22222"),
                    DUMMY_PLACE("논현역", "33333"),
                    DUMMY_PLACE("대구역", "44444"),
                )
            )
        }
    }
}