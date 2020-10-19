package com.ss.koindi.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


fun <T : Any> performNetworkRequest(networkCall: suspend () -> UseCaseResult<T>): Flow<UseCaseResult<T>> {
    return flow {
        val responseStatus = networkCall.invoke()
        emit(responseStatus)
    }.flowOn(Dispatchers.IO).cancellable()
}