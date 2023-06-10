package com.depromeet.whatnow.data.repo

import android.util.Log
import com.depromeet.whatnow.domain.utils.ApiResult
import com.depromeet.whatnow.domain.utils.ErrorType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class BaseRemoteDataSource {
    companion object{
        private const val TAG = "BaseRemoteDataSource"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }


    suspend inline fun <T> safeApiCallNoEmitter(crossinline callFunction: suspend () -> T): ApiResult<T> {
        return try {
            val result = withContext(Dispatchers.IO) { callFunction.invoke() }
            if (result == null) {
                ApiResult.Error(errorType = ErrorType.EMPTY_DATA)
            } else {
                ApiResult.Success(result)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                Log.e("BaseRemoteRepo Call error: ${e.localizedMessage} , cause : ${e.cause}","")
                var result: ApiResult.Error = when (e) {
                    is HttpException -> {
                        if (e.code() == 401) {
                            ApiResult.Error(errorType = ErrorType.SESSION_EXPIRED)
                        } else {
                            val body = e.response()?.errorBody()
                            ApiResult.Error(reason = getErrorMessage(body))
                        }
                    }
                    is SocketTimeoutException -> {
                        ApiResult.Error(errorType = ErrorType.TIMEOUT)
                    }
                    is IOException -> {
                        ApiResult.Error(errorType = ErrorType.NETWORK)

                    }
                    else -> {
                        ApiResult.Error(errorType = ErrorType.UNKNOWN)
                    }
                }
                result
            }
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(MESSAGE_KEY)
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(ERROR_KEY)
                else -> "Error"
            }
        } catch (e: Exception) {
            "Error"
        }
    }
}
