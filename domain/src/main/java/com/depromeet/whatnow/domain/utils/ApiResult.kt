package com.depromeet.whatnow.domain.utils

import java.lang.Exception
import kotlin.Result

sealed class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val exception: Exception? = null, val errorType: ErrorType? = null, val reason:String? = null) : ApiResult<Nothing>()

    override fun toString(): String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}
