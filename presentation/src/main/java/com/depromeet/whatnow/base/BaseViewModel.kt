package com.depromeet.whatnow.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kr.co.knowledgerally.log.Logger
import kr.co.knowledgerally.toast.Toaster
import kr.co.knowledgerally.ui.R
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel : ViewModel() {

    protected inline val TAG get() = this::class.java.simpleName

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            handleException(throwable)
        }
        return viewModelScope.launch(context + exceptionHandler, start = start, block = block)
    }

    protected open fun handleException(throwable: Throwable) {
        if (throwable is CancellationException) {
            return
        }
        Toaster.show(R.string.exception_common)
        Logger.e(TAG, throwable)
    }
}