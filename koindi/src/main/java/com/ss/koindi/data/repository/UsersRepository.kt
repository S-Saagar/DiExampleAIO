package com.ss.koindi.data.repository

import com.ss.koindi.data.remote.UserRemoteDataSource
import com.ss.koindi.utils.performNetworkRequest

class UsersRepository constructor(private val remoteRemoteDataSource: UserRemoteDataSource) {
    fun getUsers(result: Int) = performNetworkRequest {
        remoteRemoteDataSource.getUsers(result)
    }
}