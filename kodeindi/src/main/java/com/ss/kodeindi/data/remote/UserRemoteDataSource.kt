package com.ss.kodeindi.data.remote

class UserRemoteDataSource constructor(private val userRestApiService: UserRestApiService) :
    BaseDataSource() {
    suspend fun getUsers(results: Int) = getResult {
        userRestApiService.getUsers(results)
    }
}