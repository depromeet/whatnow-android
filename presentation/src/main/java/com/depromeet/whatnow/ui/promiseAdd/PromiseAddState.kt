package com.depromeet.whatnow.ui.promiseAdd

sealed interface PromiseAddState {
    // 약속 만들기 화면
    object MakePromise : PromiseAddState

    // 약속 상세정보 화면
    object DetailPromise: PromiseAddState
}