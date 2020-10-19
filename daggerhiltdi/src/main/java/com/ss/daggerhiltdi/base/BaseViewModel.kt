package com.ss.daggerhiltdi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ss.daggerhiltdi.utils.UseCaseResult
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.concurrent.CancellationException

abstract class BaseViewModel : ViewModel() {

    val loadingState = MutableLiveData<UseCaseResult.LoadingState>()

    private val _onError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> = _onError

    protected fun <T : Any> populateResult(
        apiResponse: Flow<UseCaseResult<T>>,
        successLiveData: MutableLiveData<T?>,
        stopLoadingOnSuccessOrError: Boolean = false
    ) = viewModelScope.launch {
        apiResponse.catch { exception ->
            emit(UseCaseResult.Error(exception = exception))
        }.collect {
            if (stopLoadingOnSuccessOrError) {
                loadingState.value = UseCaseResult.LoadingState.END
            }
            when (it) {
                is UseCaseResult.Success -> {
                    successLiveData.value = it.data
                }
                is UseCaseResult.Error -> {
                    _onError.value = it.exception
                }
                is UseCaseResult.SessionTimeOut -> {
                    //This helps to handle this common in all api result
                    //Show appropriate popup to user for re-login or logout anything else
                    viewModelScope.cancel(CancellationException("User session timeout")) //This statement cancel all request executeed
                }
                is UseCaseResult.UserUnauthorised -> {
                    //This helps to handle this common in all api result
                    //Show appropriate popup to user for re-login or logout anything else
                    viewModelScope.cancel(CancellationException("User unauthorised")) //This statement cancel all request executeed
                }
                is UseCaseResult.AppUpdate -> {
                    val forceUpdate = it.isForcefully
                    if (forceUpdate) {
                        //Stop user to do anything
                    } else {
                        //User can go chose to update now or later
                    }
                }
            }
        }
    }
}