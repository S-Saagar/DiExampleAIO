package com.ss.koindi.data.remote

import com.ss.koindi.utils.UseCaseResult
import retrofit2.Response
import java.net.HttpURLConnection

abstract class BaseDataSource {

//    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
//        try {
//            val response = call()
//            if (response.isSuccessful) {
//                val body = response.body()
//                if (body != null) return Resource.success(body)
//            }
//            return error("${response.code()} ${response.message()}")
//        } catch (e: Exception) {
//            return error(e.message ?: e.toString())
//        }
//    }
//
//    private fun <T> error(message: String): Resource<T> {
//        return Resource.error("Network call has failed for a following reason: $message")
//    }

    protected suspend fun <T : Any> getResult(call: suspend () -> Response<T>): UseCaseResult<T> {
        try {
            val response = call()
            when {
                response.isSuccessful -> {
                    val isLogout =
                        (response.headers()["is_session_timeout"] != null && response.headers()["is_session_timeout"]!!.toBoolean())

                    val isAppUpdateRequire =
                        (response.headers()["update_app"] != null && response.headers()["update_app"]!!.toBoolean())

                    val isUpdateForcefully =
                        (response.headers()["is_update_forcefully"] != null && response.headers()["is_update_forcefully"]!!.toBoolean())

                    return when {
                        isLogout -> {
                            UseCaseResult.SessionTimeOut("Session timed out")
                        }
                        isAppUpdateRequire -> {
                            UseCaseResult.AppUpdate(isUpdateForcefully)
                        }
                        else -> {
                            val body = response.body()
                            UseCaseResult.Success(body)
                        }
                    }
                }
                response.code() == HttpURLConnection.HTTP_UNAUTHORIZED -> {
                    return UseCaseResult.UserUnauthorised("User unauthorised")
                }
                else -> {
                    return UseCaseResult.Error(Exception("${response.code()} ${response.message()}"))
                }
            }
        } catch (e: Exception) {
            return UseCaseResult.Error(e)
        }
    }

}