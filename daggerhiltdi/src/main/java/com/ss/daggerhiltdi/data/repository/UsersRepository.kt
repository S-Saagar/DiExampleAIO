package com.ss.daggerhiltdi.data.repository

import com.ss.daggerhiltdi.data.remote.UserRemoteDataSource
import com.ss.daggerhiltdi.utils.performNetworkRequest
import javax.inject.Inject

class UsersRepository @Inject constructor(private val remoteRemoteDataSource: UserRemoteDataSource) {
    fun getUsers(result: Int) = performNetworkRequest {
        remoteRemoteDataSource.getUsers(result)
    }
}