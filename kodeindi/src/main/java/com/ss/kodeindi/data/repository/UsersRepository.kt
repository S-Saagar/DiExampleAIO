package com.ss.kodeindi.data.repository

import com.ss.kodeindi.data.remote.UserRemoteDataSource
import com.ss.kodeindi.utils.performNetworkRequest


class UsersRepository constructor(private val remoteRemoteDataSource: UserRemoteDataSource) {
    fun getUsers(result: Int) = performNetworkRequest {
        remoteRemoteDataSource.getUsers(result)
    }
}