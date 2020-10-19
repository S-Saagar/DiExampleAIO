package com.ss.daggerhiltdi.utils

sealed class UseCaseResult<out T : Any> {
    data class Success<out T : Any>(val data: T?) : UseCaseResult<T>()
    data class Error(val exception: Throwable) : UseCaseResult<Nothing>()
    data class SessionTimeOut(val message: String) : UseCaseResult<Nothing>()
    data class UserUnauthorised(val message: String) : UseCaseResult<Nothing>()
    data class AppUpdate(val isForcefully: Boolean) : UseCaseResult<Nothing>()

    enum class LoadingState {
        START,
        END
    }
}