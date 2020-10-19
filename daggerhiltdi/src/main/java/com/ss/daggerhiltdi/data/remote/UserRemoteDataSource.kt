package com.ss.daggerhiltdi.data.remote

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val userRestApiService: UserRestApiService) :
    BaseDataSource() {

    suspend fun getUsers(results: Int) = getResult {
        userRestApiService.getUsers(results)
    }
}