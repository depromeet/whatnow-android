package com.depromeet.whatnow.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
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
        return viewModelScope.launch(
            context = context + exceptionHandler,
            start = start,
            block = block
        )
    }

    protected open fun handleException(throwable: Throwable) {
        if (throwable is CancellationException) return
        Log.e(TAG, throwable.stackTraceToString())

    }
}