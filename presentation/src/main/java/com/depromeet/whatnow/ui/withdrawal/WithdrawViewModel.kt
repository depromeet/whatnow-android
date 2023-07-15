package com.depromeet.whatnow.ui.withdrawal

import com.depromeet.whatnow.base.BaseViewModel
import com.depromeet.whatnow.domain.usecase.WithdrawUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    private val withdrawUseCase: WithdrawUseCase
) : BaseViewModel() {

    fun withdraw() {
        launch {
            withdrawUseCase().getOrThrow()
        }
    }
}
