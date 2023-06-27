package com.depromeet.whatnow.ui.promiseAdd

import com.depromeet.whatnow.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PromiseAddViewModel @Inject constructor() : BaseViewModel() {

    private val itemList = mutableListOf<PromiseAddPlace>()

    init {
        val asdasd = PromiseAddPlace(placeAddress = "asd", placeTitle = "asdasd")
        for (i in 1..5) {
            itemList.add(asdasd)
        }
    }

    fun getItemList(): List<PromiseAddPlace> {
        return itemList
    }
}